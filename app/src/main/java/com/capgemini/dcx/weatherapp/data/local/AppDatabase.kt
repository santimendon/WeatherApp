package com.capgemini.dcx.weatherapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capgemini.dcx.weatherapp.data.local.dao.SearchHistoryDao
import com.capgemini.dcx.weatherapp.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.weatherapp.util.DATABASE_NAME

/**
 * Created by Sanket Mendon on 2020-05-03,
 * sanket.mendon@gmail.com
 */

@Database(entities = arrayOf(SearchHistoryItem::class), version = 1, exportSchema = false)

abstract class AppDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}