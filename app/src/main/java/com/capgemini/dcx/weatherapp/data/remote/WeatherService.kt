package com.capgemini.dcx.assisgnment.data.remote

import com.capgemini.dcx.assisgnment.data.remote.models.searchmodel.SearchResponse
import com.capgemini.dcx.assisgnment.util.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WeatherService {
    @GET("/$SUBSCRIPTION_TYPE/$API_VERSION/search.ashx")
    suspend fun searchCity(
        @Query("query") city: String,
        @Query("num_of_results") numOfResults: Int = NUM_OF_RESULTS,
        @Query("format") format: String = FORMAT,
        @Query("key") key: String = API_KEY
    ): Response<SearchResponse>


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