package com.mursyidul.submissionweek8.local.pustaka

import androidx.room.*


@Dao
interface PustakaDao {
    @Query(value = "SELECT * FROM pustaka" )
    fun getDataPustaka():List<Pustaka>

    @Insert
    fun insertPustaka(pustaka: Pustaka?)

    @Update
    fun updatePustaka(pustaka: Pustaka?)

    @Delete
    fun deletePustaka(pustaka: Pustaka?)

}