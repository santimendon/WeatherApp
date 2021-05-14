package com.smendon.android.weatherapp.data.remote.models.searchmodel


data class SearchResponse(
    val search_api: ListResult,
    val data: Data
)

/*
* For Search API no results found error
*
*/
data class Error(
    val msg: String
)

/*
* For Search API no results found error
*
*/
data class Data(
    val error: List<Error>
)

data class ListResult(
    val result: List<SearchItem>
)

data class SearchItem(
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
)