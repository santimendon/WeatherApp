package com.smendon.android.weatherapp.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val KEY_USER_LOCATION = "user_location"
private const val DEFAULT_LOCATION = "New York United States"


/**
 * Created by Sanket Mendon on 2020-05-04,
 * sanket.mendon@gmail.com
 */
class PreferenceStore(
    context: Context
) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun setUserLocation(value: String) {
        preference.edit().putString(
            KEY_USER_LOCATION,
            value
        ).apply()
    }

    fun getUserLocation(): String? {
        return preference.getString(KEY_USER_LOCATION, DEFAULT_LOCATION)
    }

}