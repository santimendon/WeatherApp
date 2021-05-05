package com.capgemini.dcx.weatherapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capgemini.dcx.weatherapp.data.local.entities.SearchHistoryItem

/**
 * Created by Sanket Mendon on 2020-05-03,
 * sanket.mendon@gmail.com
 */

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchItem: SearchHistoryItem)

    @Query("UPDATE table_search_history SET updateTime = :newTime WHERE updateTime = :originalTime")
    suspend fun update(newTime: Long, originalTime: Long)

    @Query("SELECT * FROM table_search_history ORDER BY updateTime DESC")
    fun getSearchHistory(): LiveData<List<SearchHistoryItem>>

}