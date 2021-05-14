package com.smendon.android.weatherapp.view.weatherdashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smendon.android.weatherapp.data.remote.models.currentweathermodel.Month
import com.smendon.android.weatherapp.databinding.MonthlyWeatherItemBinding


/**
 * Created by Sanket Mendon on 2020-05-03,
 * sanket.mendon@gmail.com
 */
class MonthlyWeatherItemAdapter() :
    ListAdapter<Month, MonthlyWeatherItemAdapter.MonthlyWeatherItemViewModel>(Companion) {

    class MonthlyWeatherItemViewModel(val binding: MonthlyWeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthlyWeatherItemViewModel {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MonthlyWeatherItemBinding.inflate(layoutInflater)
        binding.root.layoutParams = ViewGroup.LayoutParams(
            (parent.width * 0.3).toInt(),
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        return MonthlyWeatherItemViewModel(binding)
    }

    override fun onBindViewHolder(holder: MonthlyWeatherItemViewModel, position: Int) {
        val currentItem = getItem(position)
        holder.binding.monthlyWeatherItem = currentItem
        holder.binding.executePendingBindings()
    }

    companion object : DiffUtil.ItemCallback<Month>() {
        override fun areItemsTheSame(oldItem: Month, newItem: Month): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(
            oldItem: Month,
            newItem: Month
        ): Boolean =
            oldItem == newItem
    }
}