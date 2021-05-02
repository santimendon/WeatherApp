package com.capgemini.dcx.assisgnment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capgemini.dcx.assisgnment.data.local.entities.SearchHistoryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(searchItem: SearchHistoryItem)

    @Query("SELECT * FROM table_search_history")
    fun getSearchHistory(): LiveData<List<SearchHistoryItem>>

}