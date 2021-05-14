package com.smendon.android.weatherapp.util

import com.smendon.android.weatherapp.BuildConfig

const val WORLD_WEATHER_API_KEY = BuildConfig.API_KEY

/*
 * @HOST https://www.worldweatheronline.com/
 * @SUBSCRIPTION_TYPE premium/free
 * @API_VERSION v1/v2
 * Base URL for Retrofit object
 */
const val HOST = "http://api.worldweatheronline.com"
const val SUBSCRIPTION_TYPE = "premium"
const val API_VERSION = "v1"

const val BASE_URL = HOST

const val ENDPOINT_SEARCH = "search.ashx"
const val ENDPOINT_CURRENT_WEATHER = "weather.ashx"

/*
 * API Query's
 */
const val KEY_QUERY = "query"
const val KEY_NUM_RESULTS = "num_of_results"
const val KEY_FORMAT = "format"
const val KEY_API_KEY = "key"
const val KEY_NUM_DAYS = "num_of_days"
const val KEY_SHOW_LOCAL_TIME = "showlocaltime"

/*
* Defaukt API Query values
*/
const val NUM_OF_RESULTS = 10 //Search results count
const val NUM_OF_DAYS = 7  //Weather for last x days
const val FLAG_SHOW_LOCAL_TIME_VAL = "yes" //Flag to show local time
const val API_RESPONSE_FORMAT = "json" //API response format (supported formats: xml/json/tab)

/**
 * NETWORK TIMEOUT
 */
const val HTTP_CONNECTION_TIMEOUT: Long = 120
const val HTTP_READ_TIMEOUT: Long = 60
const val HTTP_WRITE_TIMEOUT: Long = 60

/**
 * Database name
 */
const val DATABASE_NAME = "weatherappdb.db"