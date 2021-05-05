package com.capgemini.dcx.weatherapp

import android.app.Application
import com.capgemini.dcx.weatherapp.data.local.AppDatabase
import com.capgemini.dcx.weatherapp.data.local.PreferenceStore
import com.capgemini.dcx.weatherapp.data.remote.NetworkInterceptor
import com.capgemini.dcx.weatherapp.data.remote.WeatherService
import com.capgemini.dcx.weatherapp.data.repositories.AppRepository
import com.capgemini.dcx.weatherapp.view.history.SharedViewModelFactory
import com.capgemini.dcx.weatherapp.view.weatherdashboard.WeatherDashboardModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Created by Sanket Mendon on 2020-05-01,
 * sanket.mendon@gmail.com
 */

class WeatherApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))

        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { WeatherService(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { AppRepository(instance(), instance(), instance()) }
        bind() from singleton { PreferenceStore(instance()) }
        bind() from provider { WeatherDashboardModelFactory(instance()) }
        bind() from provider { SharedViewModelFactory(instance()) }
    }
}