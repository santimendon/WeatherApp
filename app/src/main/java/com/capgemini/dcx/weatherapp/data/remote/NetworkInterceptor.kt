package com.capgemini.dcx.assisgnment.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.capgemini.dcx.assisgnment.util.NetworkConnectionException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    context: Context
) : Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isInternetAvailable())
            throw NetworkConnectionException("Make sure you have an active data connection")
        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                        else -> false
                    }
                }
            } else {
                TODO("VERSION.SDK_INT < M")
            }
        }
        return result
    }

}