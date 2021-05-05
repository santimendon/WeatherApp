package com.capgemini.dcx.weatherapp.data.remote

import com.capgemini.dcx.weatherapp.data.remote.models.currentweathermodel.CurrentWeather
import com.capgemini.dcx.weatherapp.data.remote.models.searchmodel.SearchResponse
import com.capgemini.dcx.weatherapp.util.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Sanket Mendon on 2020-05-01,
 * sanket.mendon@gmail.com
 */
interface WeatherService {
    @GET("/$SUBSCRIPTION_TYPE/$API_VERSION/$ENDPOINT_SEARCH")
    suspend fun searchCity(
        @Query(KEY_QUERY) city: String,
        @Query(KEY_NUM_RESULTS) numOfResults: Int = NUM_OF_RESULTS,
        @Query(KEY_FORMAT) format: String = API_RESPONSE_FORMAT,
        @Query(KEY_API_KEY) key: String = WORLD_WEATHER_API_KEY
    ): Response<SearchResponse>

    @GET("/$SUBSCRIPTION_TYPE/$API_VERSION/$ENDPOINT_CURRENT_WEATHER")
    suspend fun getCurrentWeather(
        @Query(KEY_QUERY) place: String,
        @Query(KEY_NUM_DAYS) numOfResults: Int = NUM_OF_DAYS,
        @Query(KEY_FORMAT) format: String = API_RESPONSE_FORMAT,
        @Query(KEY_SHOW_LOCAL_TIME) showLocalTime: String = FLAG_SHOW_LOCAL_TIME_VAL,
        @Query(KEY_API_KEY) key: String = WORLD_WEATHER_API_KEY
    ): Response<CurrentWeather>


    companion object {
        operator fun invoke(networkInterceptor: NetworkInterceptor): WeatherService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(networkInterceptor)
                .connectTimeout(HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)
        }
    }
}