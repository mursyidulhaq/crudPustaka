package com.mursyidul.submissionweek8.repository

import android.content.Context
import com.mursyidul.submissionweek8.local.pustaka.Pustaka
import com.mursyidul.submissionweek8.local.pustaka.PustakaDb
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class PustakaRepository(val context: Context) {

    private var pustakaDb = PustakaDb.getInstance(context)

    fun showPustaka(responseHandler: (List<Pustaka>) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { pustakaDb.pustakaDao().getDataPustaka() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ item ->
                responseHandler(item)

            }, {
                errorHandler(it)
            })

    }


    fun addPustaka(id:Int?,
                    nama:String,
                    judul:String,
                    tanggalpinjam:String,
                    responseHandler: (item:Pustaka)->Unit,
                    errorHandler: (Throwable) -> Unit)
    {
        Observable.fromCallable {
            pustakaDb.pustakaDao().insertPustaka(Pustaka(id,nama,judul,tanggalpinjam)) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(Pustaka(id,nama,judul,tanggalpinjam))


            },{
                errorHandler(it)
            })
    }

    fun  updatePustaka(id: Int?,nama: String,judul: String,tanggalpinjam :String, responseHandler: (item: Pustaka) -> Unit,
                        errorHandler: (Throwable) -> Unit){
        Observable.fromCallable {
            pustakaDb.pustakaDao().updatePustaka(Pustaka(id,nama,judul,tanggalpinjam))
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(Pustaka(id,nama,judul,tanggalpinjam))
            },{
                errorHandler(it)
            })
    }

    fun deletePustaka(item :Pustaka,responseHandler: (item: Pustaka) -> Unit,errorHandler: (Throwable) -> Unit){
        Observable.fromCallable {
            pustakaDb.pustakaDao().deletePustaka(item)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(item)


            }, {
                errorHandler(it)
            })
    }


}