package com.mursyidul.submissionweek8.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {


    @Query( "SELECT * FROM user_data WHERE user_email=:email AND user_password=:password LIMIT 1")
    fun login(email: String?,password:String?):User


    @Query("SELECT * FROM user_data WHERE user_email=:email LIMIT 1")
    fun getUser(email: String?):User


    @Insert
    fun register(user: User)
}