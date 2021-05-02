package com.capgemini.dcx.assisgnment.view.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capgemini.dcx.assisgnment.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.assisgnment.data.remote.models.searchmodel.SearchResponse
import com.capgemini.dcx.assisgnment.data.repositories.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SearchViewModel(private val repository: AppRepository) : ViewModel() {
    private val _searchData = MutableLiveData<SearchResponse>()
    val searchData: LiveData<SearchResponse> = _searchData

    val searchHistory: LiveData<List<SearchHistoryItem>> = repository.getSearchHistory

    suspend fun searchCity(city: String) = withContext(Dispatchers.IO) {
        withContext(Dispatchers.Main) {
            _searchData.value = repository.searchCity(city)
        }
    }

    suspend fun saveSearchItem(searchItem: SearchHistoryItem) = withContext(Dispatchers.IO) {
        Log.d("TAG", "On click")
        repository.saveHistoryItem(searchItem)
    }
}