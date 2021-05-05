package com.capgemini.dcx.weatherapp.view.weatherdashboard

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capgemini.dcx.weatherapp.data.remote.models.currentweathermodel.CurrentWeather
import com.capgemini.dcx.weatherapp.data.remote.models.currentweathermodel.Month
import com.capgemini.dcx.weatherapp.data.remote.models.currentweathermodel.Weather
import com.capgemini.dcx.weatherapp.data.repositories.AppRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat

/**
 * Created by Sanket Mendon on 2020-05-02,
 * sanket.mendon@gmail.com
 */
class WeatherDashboardViewModel(private val repository: AppRepository) : ViewModel() {
    private val _currentWeatherData = MutableLiveData<CurrentWeather>()
    val currentWeatherData: LiveData<CurrentWeather> = _currentWeatherData


    suspend fun getCurrentWeather(place: String) = withContext(Dispatchers.IO) {
        _currentWeatherData.postValue(repository.getCurrentWeather(place))
    }

    fun getUserLocation() = repository.getUserLocation()
}

@BindingAdapter("weatherTypeLogo")
fun loadImage(view: AppCompatImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}

@BindingAdapter("populateData")
fun bindRecyclerView(recyclerView: RecyclerView?, data: List<Weather>?) {
    val adapter = recyclerView?.adapter as? DailyWeatherItemAdapter
    if (adapter != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("populateMonthlyData")
fun bindMonthData(recyclerView: RecyclerView?, data: List<Month>?) {
    val adapter = recyclerView?.adapter as? MonthlyWeatherItemAdapter
    if (adapter != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("bindServerDate")
fun setDate(view: AppCompatTextView?, responseDate: String?) {
    responseDate?.let {
        var fromServer = SimpleDateFormat("yyyy-MM-dd HH:ss")
        var myFormat = SimpleDateFormat("HH:mm, dd MMM")
        if (view != null) {
            view.text = myFormat.format(fromServer.parse(responseDate))
        }
    }
}

