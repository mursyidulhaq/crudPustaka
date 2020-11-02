package com.mursyidul.submissionweek8.local.pustaka

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mursyidul.submissionweek8.local.user.User
import com.mursyidul.submissionweek8.local.user.UserDao

@Database(entities = [Pustaka::class,User::class],version = 2)
abstract class PustakaDb : RoomDatabase() {

    abstract fun  pustakaDao (): PustakaDao
    abstract fun userDao(): UserDao

    companion object{

        private var INSTANCE : PustakaDb? = null
        fun getInstance(context: Context):PustakaDb {
            synchronized(this) {
                var instance: PustakaDb? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PustakaDb::class.java,
                        "pustakaDb.db"
                    )

                        .fallbackToDestructiveMigrationFrom(1,2,3)
                        .build()
                }

                return instance
            }
        }


    }



}