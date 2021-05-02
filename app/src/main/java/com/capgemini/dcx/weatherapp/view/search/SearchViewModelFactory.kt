package com.capgemini.dcx.assisgnment.view.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capgemini.dcx.assisgnment.data.repositories.AppRepository

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(repository) as T
    }
}