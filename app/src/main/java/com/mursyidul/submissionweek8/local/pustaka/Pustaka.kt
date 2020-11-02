package com.mursyidul.submissionweek8.local.pustaka

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pustaka")
data class Pustaka (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_pustaka")
    val id_pustaka : Int? = null,

    @ColumnInfo(name = "nama")
    val nama_peminjam : String?,

    @ColumnInfo(name = "judul_buku")
    val judul : String?,
    @ColumnInfo(name = "tanggal_pinjam")
    val tanggal_pinjam : String?


)