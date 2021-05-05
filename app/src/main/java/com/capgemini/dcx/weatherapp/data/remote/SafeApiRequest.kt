package com.capgemini.dcx.weatherapp.data.remote

import com.capgemini.dcx.weatherapp.util.RemoteException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by Sanket Mendon on 2020-05-01,
 * sanket.mendon@gmail.com
 */
abstract class SafeApiRequest {

    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val error = response.errorBody()?.string()
            val message = StringBuilder()
            error?.let {
                try {
                    message.append(JSONObject(it).getString("message"))
                } catch (e: JSONException) {
                }
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw RemoteException(message.toString())
        }
    }

}