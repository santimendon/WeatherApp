package com.capgemini.dcx.assisgnment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.capgemini.dcx.assisgnment.data.local.dao.SearchHistoryDao
import com.capgemini.dcx.assisgnment.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.assisgnment.util.DATABASE_NAME

@Database(entities = arrayOf(SearchHistoryItem::class), version = 1, exportSchema = false)

abstract class ApplicationRoomDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchHistoryDao

    companion object {

        @Volatile
        private var instance: ApplicationRoomDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ApplicationRoomDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}