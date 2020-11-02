package com.mursyidul.submissionweek8.view.home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mursyidul.submissionweek8.R
import com.mursyidul.submissionweek8.adapter.PustakaAdapter
import com.mursyidul.submissionweek8.local.pustaka.Pustaka
import com.mursyidul.submissionweek8.viewmodel.PustakaViewModel
import kotlinx.android.synthetic.main.dialog_add_pustaka.*
import kotlinx.android.synthetic.main.dialog_add_pustaka.view.*
import kotlinx.android.synthetic.main.fragment_pustaka.*
import java.text.SimpleDateFormat
import java.util.*


class PustakaFragment : Fragment() {

    private var dialogView: Dialog? = null
    private lateinit var pustkaViewModel: PustakaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pustaka, container, false)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pustkaViewModel = ViewModelProvider(this).get(PustakaViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pustkaViewModel.getListPustaka()

        attachObserve()

        fab.setOnClickListener {
            showAddDialog()

        }
    }

    private fun showAddDialog() {
        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_add_pustaka, null)
        dialog.setView(view)

        view.editTgl.setText(getDate())
        view.btnSimpan.setOnClickListener {

            pustkaViewModel.addPustaka(null,getDate(),
                view.editTextJudul.text.toString(),
                view.editNama.text.toString()
                )

        }

        view.btnClose.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }

    private fun getDate(): String {
        val date = Calendar.getInstance().time
        val format = SimpleDateFormat.getDateInstance()
        val formatDate = format.format(date)
        return formatDate
    }

    private fun attachObserve() {

        //getData
        pustkaViewModel._responseData.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showPustaka(it) })
        pustkaViewModel._isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showError(it) })

        //input
        pustkaViewModel._responseAction.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { inputTabungan(it) })
        pustkaViewModel._isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { inputError(it) })
        pustkaViewModel.empty_nama.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { emptyNama() })

        pustkaViewModel.empty_judul.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { emptyJudul() })

        pustkaViewModel.empty_tanggalpinjam.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { emptyTanggalPinjam() })


    }



    private fun emptyTanggalPinjam() {

        dialogView?.editTgl?.error = "masukan nama"

    }

    private fun emptyJudul() {

        dialogView?.editTextJudul?.error = "masukan nama"

    }

    private fun emptyNama() {
        dialogView?.editNama?.error = "masukan nama"


    }

    private fun inputError(it: Throwable?) {
        Log.d("TAG", "inputTabungan: gagal")
    }

    private fun inputTabungan(it: Pustaka?) {
        Log.d("TAG", "inputTabungan: OK")
        dialogView?.dismiss()
        pustkaViewModel.getListPustaka()
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showPustaka(it: List<Pustaka>) {
        listPustaka.adapter = PustakaAdapter(it, object : PustakaAdapter.OnClickListener {

                override fun update(item: Pustaka?) {
                    showUpdatePustaka(item)
                }

                override fun delete(item: Pustaka?) {
                    AlertDialog.Builder(context).apply {
                        setTitle("Hapus")
                        setMessage("Yakin hapus data ?")
                        setCancelable(false)

                        setPositiveButton("Yakin") { dialogInterface, i ->
                            if (item != null) {
                                pustkaViewModel.deletePustaka(item)
                            }
                        }
                        setNegativeButton("Batal") { dialogInterface, i ->
                            dialogInterface.dismiss()
                        }
                    }.show()
                }
            })
    }

    private fun showUpdatePustaka(item :Pustaka?) {
        val dialog = AlertDialog.Builder(context)
        val view = layoutInflater.inflate(R.layout.dialog_add_pustaka, null)
        dialog.setView(view)

        view.btnSimpan.text = "Update"
        view.editNama.setText((item?.tanggal_pinjam))
        view.editTextJudul.setText((item?.judul))
        view.editTgl.setText((item?.nama_peminjam))


        view.btnSimpan.setOnClickListener {
            pustkaViewModel.updatePustaka(item?.id_pustaka,getDate(),
                view.editTextJudul.text.toString(),
                view.editNama.text.toString()
                )

        }
        view.btnClose.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()
    }

}