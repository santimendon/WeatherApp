package com.capgemini.dcx.assisgnment.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.capgemini.dcx.assisgnment.data.remote.models.searchmodel.SearchItem

@Entity(tableName = "table_search_history")
data class SearchHistoryItem(
    @PrimaryKey
    val areaName: String,
    val country: String,
    val latitude: String,
    val longitude: String,
    val population: String,
    val region: String,
    val weatherUrl: String
)

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

/*

@Entity(tableName = "table_search_history")
data class SearchItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val areaName: List<AreaName>,
    val country: List<Country>,
    val latitude: String,
    val longitude: String,
    val population: String,
    val region: List<Region>,
    val weatherUrl: List<WeatherUrl>
)

data class AreaName(
    val value: String
)

data class Country(
    val value: String
)

data class Region(
    val value: String
)

data class WeatherUrl(
    val value: String
)*/
