package com.capgemini.dcx.assisgnment.data.repositories

import androidx.lifecycle.LiveData
import com.capgemini.dcx.assisgnment.data.local.ApplicationRoomDatabase
import com.capgemini.dcx.assisgnment.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.assisgnment.data.remote.SafeApiRequest
import com.capgemini.dcx.assisgnment.data.remote.WeatherService
import com.capgemini.dcx.assisgnment.data.remote.models.searchmodel.SearchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AppRepository(
    private val apiService: WeatherService,
    private val database: ApplicationRoomDatabase
) : SafeApiRequest() {

    suspend fun searchCity(
        city: String
    ): SearchResponse {
        return apiRequest { apiService.searchCity(city) }
    }


    suspend fun saveHistoryItem(searchItem: SearchHistoryItem) {
        CoroutineScope(Dispatchers.IO).launch {
            database.searchDao().insert(searchItem)
        }
    }

    val getSearchHistory: LiveData<List<SearchHistoryItem>> = database.searchDao().getSearchHistory()
}