package com.mursyidul.submissionweek8.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mursyidul.submissionweek8.local.pustaka.Pustaka
import com.mursyidul.submissionweek8.repository.PustakaRepository

class PustakaViewModel(application: Application) :AndroidViewModel(application) {


    val context: Context = application
    val repository = PustakaRepository(application.applicationContext)
    var responseData = MutableLiveData<List<Pustaka>>()
    var _responseData: LiveData<List<Pustaka>> = responseData

    var responseAction = MutableLiveData<Pustaka>()
    var _responseAction: LiveData<Pustaka> = responseAction

    var isError = MutableLiveData<Throwable>()
    var _isError: LiveData<Throwable> = isError

    var _empty_nama = MutableLiveData<Boolean>()
    var empty_nama: LiveData<Boolean> = _empty_nama

    var _empty_judul = MutableLiveData<Boolean>()
    var empty_judul: LiveData<Boolean> = _empty_judul

    var _empty_tanggalpinjam = MutableLiveData<Boolean>()
    var empty_tanggalpinjam: LiveData<Boolean> = _empty_tanggalpinjam


    var _wrong_email = MutableLiveData<Boolean>()
    var wrong_email: LiveData<Boolean> = _wrong_email





    fun getListPustaka() {
        repository.showPustaka({
            responseData.value = it

        }, {
            isError.value = it

        })
    }

    fun addPustaka(id: Int?, nama: String, judul: String, tanggalpinjam: String) {

        if (nama.isEmpty()) {
            _empty_nama.value = true
        } else if (judul.isEmpty()) {
            _empty_judul.value = true
        }else if(tanggalpinjam.isEmpty()){
            _empty_tanggalpinjam.value
        }
        else {
            repository.addPustaka(id, nama,judul,tanggalpinjam, {
                responseAction.value = it
                Toast.makeText(context, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()

            }, {
                isError.value = it
            })
        }

    }

    fun updatePustaka(id: Int?, nama: String, judul: String, tanggalpinjam: String) {
        if (nama.isEmpty()) {
            _empty_nama.value = true
        } else if (judul.isEmpty()) {
            _empty_judul.value = true
        }else if(tanggalpinjam.isEmpty()){
            _empty_tanggalpinjam.value
        }else {
            repository.updatePustaka(id, nama, judul,tanggalpinjam,{
                responseAction.value = it
                Toast.makeText(context, "Data berhasil diubah", Toast.LENGTH_SHORT).show()
            }, {
                isError.value = it
            })
        }
    }

    fun deletePustaka(item: Pustaka) {
        repository.deletePustaka(item, {
            responseAction.value = it
            Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
        }, {
            isError.value = it
        })
    }

}