package com.example.pruebjuan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pruebjuan.AppContext
import com.example.pruebjuan.models.User
import com.example.pruebjuan.models.daos.UserDao


@Database(entities = [User::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: MyDatabase? = null
        private const val DATABASE_NAME = "my-database.db"

        fun getDatabase(): MyDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    AppContext.getContext(), MyDatabase::class.java, DATABASE_NAME
                ).allowMainThreadQueries().build()
            }
            return INSTANCE!!
        }
    }
}
