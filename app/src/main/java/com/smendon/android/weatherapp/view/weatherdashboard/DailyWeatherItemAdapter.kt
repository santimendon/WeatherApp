package com.smendon.android.weatherapp.view.weatherdashboard

import android.os.Build
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smendon.android.weatherapp.data.remote.models.currentweathermodel.Weather
import com.smendon.android.weatherapp.databinding.DailyWeatherItemBinding
import java.text.SimpleDateFormat

/**
 * Created by Sanket Mendon on 2020-05-02,
 * sanket.mendon@gmail.com
 */
class DailyWeatherItemAdapter() :
    ListAdapter<Weather, DailyWeatherItemAdapter.DailyWeatherItemViewModel>(Companion) {

    class DailyWeatherItemViewModel(val binding: DailyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWeatherItemViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DailyWeatherItemBinding.inflate(layoutInflater)

        //For increasing width of item
        binding.root.layoutParams = ViewGroup.LayoutParams(
            (parent.width * 0.4).toInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return DailyWeatherItemViewModel(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: DailyWeatherItemViewModel, position: Int) {
        val currentItem = getItem(position)
        holder.binding.dailyWeatherItem = currentItem
        holder.binding.textDate.text = bindDate(currentItem.date)
        holder.binding.executePendingBindings()
    }

    companion object : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: Weather,
            newItem: Weather
        ): Boolean =
            oldItem == newItem
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun bindDate(responseDate: String?): String? {
        return responseDate?.let {
            val format = SimpleDateFormat("yyyy-MM-dd")
            val date = format.parse(responseDate)
            val day = DateFormat.format("dd", date)
            val month = DateFormat.format("MMM", date)

            return "$day $month"

        }
    }
}