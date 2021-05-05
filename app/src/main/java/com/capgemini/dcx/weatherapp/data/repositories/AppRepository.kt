package com.capgemini.dcx.weatherapp.data.repositories

import androidx.lifecycle.LiveData
import com.capgemini.dcx.weatherapp.data.local.AppDatabase
import com.capgemini.dcx.weatherapp.data.local.PreferenceStore
import com.capgemini.dcx.weatherapp.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.weatherapp.data.remote.SafeApiRequest
import com.capgemini.dcx.weatherapp.data.remote.WeatherService
import com.capgemini.dcx.weatherapp.data.remote.models.currentweathermodel.CurrentWeather
import com.capgemini.dcx.weatherapp.data.remote.models.searchmodel.SearchResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Sanket Mendon on 2020-05-01,
 * sanket.mendon@gmail.com
 */
class AppRepository(
    private val apiService: WeatherService,
    private val database: AppDatabase,
    private val preferenceStore: PreferenceStore
) : SafeApiRequest() {

    suspend fun searchCity(
        city: String
    ): SearchResponse {
        return apiRequest { apiService.searchCity(city) }
    }

    suspend fun getCurrentWeather(
        place: String
    ): CurrentWeather {
        return apiRequest { apiService.getCurrentWeather(place) }
    }


    suspend fun saveHistoryItem(searchItem: SearchHistoryItem) {
        CoroutineScope(Dispatchers.IO).launch {
            database.searchDao().insert(searchItem)
        }
    }

    suspend fun updateHistoryItem(newTime: Long, originalTime: Long) {
        CoroutineScope(Dispatchers.IO).launch {
            database.searchDao().update(newTime, originalTime)
        }
    }

    val getSearchHistory: LiveData<List<SearchHistoryItem>> =
        database.searchDao().getSearchHistory()

    fun setUserLocation(location: String) {
        preferenceStore.setUserLocation(location)
    }

    fun getUserLocation(): String? = preferenceStore.getUserLocation()
}