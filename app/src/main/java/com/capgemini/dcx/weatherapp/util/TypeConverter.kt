package com.capgemini.dcx.assisgnment.util

import com.capgemini.dcx.assisgnment.data.local.entities.SearchHistoryItem
import com.capgemini.dcx.assisgnment.data.remote.models.searchmodel.SearchItem

class TypeConverter {

    companion object fun transformToSearchHistory(obj: SearchItem): SearchHistoryItem {
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
}