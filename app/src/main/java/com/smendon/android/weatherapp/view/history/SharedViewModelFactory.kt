package com.smendon.android.weatherapp.view.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smendon.android.weatherapp.data.repositories.AppRepository

/**
 * Created by Sanket Mendon on 2020-05-03,
 * sanket.mendon@gmail.com
 */

@Suppress("UNCHECKED_CAST")
class SharedViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}