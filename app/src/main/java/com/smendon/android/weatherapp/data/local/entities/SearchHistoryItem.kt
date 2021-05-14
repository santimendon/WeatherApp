package com.smendon.android.weatherapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.smendon.android.weatherapp.data.remote.models.searchmodel.SearchItem

@Entity(tableName = "table_search_history")
data class SearchHistoryItem(
    val areaName: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val population: String,
    val region: String,
    val weatherUrl: String
) {
    @PrimaryKey
    var updateTime: Long? = System.currentTimeMillis()
}

fun transformToSearchHistory(obj: SearchItem): SearchHistoryItem {
    val areaName = obj.areaName.get(0).value
    val country = obj.country.get(0).value
    val latitude = obj.latitude
    val longitude = obj.longitude
    val population = obj.population
    val region = obj.region.get(0).value
    val weatherUrl = obj.weatherUrl.get(0).value

    return SearchHistoryItem(
        areaName,
        country,
        latitude,
        longitude,
        population,
        region,
        weatherUrl
    )
}