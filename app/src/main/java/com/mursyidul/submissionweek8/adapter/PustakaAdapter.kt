package com.mursyidul.submissionweek8.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mursyidul.submissionweek8.R
import com.mursyidul.submissionweek8.local.pustaka.Pustaka
import kotlinx.android.synthetic.main.list_pustaka.view.*

class PustakaAdapter(private val data : List<Pustaka>,private val itemClick :OnClickListener) : RecyclerView.Adapter<PustakaAdapter.PustakaHolder>() {
    class PustakaHolder(val view : View,val itemClick: OnClickListener) :RecyclerView.ViewHolder(view) {

        fun bind(item: Pustaka?){
            view.txtnamaPeminjam.text = item?.tanggal_pinjam
            view.txtjudul.text=item?.judul
            view.txttanggalpinjam.text=item?.nama_peminjam

            view.btnedit.setOnClickListener {
                itemClick.update(item)
            }
            view.btndelete.setOnClickListener {
                itemClick.delete(item)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PustakaHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.list_pustaka,parent,false)
        return PustakaHolder(view,itemClick)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: PustakaHolder, position: Int) {
        val item : Pustaka? = data?.get(position)
        holder.bind(item)

    }

    interface OnClickListener{
        fun update(item: Pustaka?)
        fun delete(item: Pustaka?)
    }

}