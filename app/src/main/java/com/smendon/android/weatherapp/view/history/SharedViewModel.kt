package com.smendon.android.weatherapp.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smendon.android.weatherapp.data.local.entities.SearchHistoryItem
import com.smendon.android.weatherapp.data.remote.models.searchmodel.SearchResponse
import com.smendon.android.weatherapp.data.repositories.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Sanket Mendon on 2020-05-03,
 * sanket.mendon@gmail.com
 */

class SharedViewModel(private val repository: AppRepository) : ViewModel() {
    private var _searchData = MutableLiveData<SearchResponse>()
    val searchData: LiveData<SearchResponse> = _searchData

    val searchHistory: LiveData<List<SearchHistoryItem>> = repository.getSearchHistory

    suspend fun searchCity(city: String) = withContext(Dispatchers.IO) {
        _searchData.postValue(repository.searchCity(city))
    }

    suspend fun saveSearchItem(searchItem: SearchHistoryItem) = withContext(Dispatchers.IO) {
        repository.setUserLocation(searchItem.areaName + ' ' + searchItem.country)
        repository.saveHistoryItem(searchItem)
    }

    suspend fun updateHistoryItem(item: SearchHistoryItem) = withContext(Dispatchers.IO) {
        repository.setUserLocation(item.areaName + ' ' + item.country)
        item.updateTime?.let {
            repository.updateHistoryItem(
                newTime = System.currentTimeMillis(),
                originalTime = it
            )
        }
    }

}