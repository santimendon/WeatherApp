package com.capgemini.dcx.assisgnment

import android.app.Application
import com.capgemini.dcx.assisgnment.data.local.ApplicationRoomDatabase
import com.capgemini.dcx.assisgnment.data.remote.NetworkInterceptor
import com.capgemini.dcx.assisgnment.data.remote.WeatherService
import com.capgemini.dcx.assisgnment.data.repositories.AppRepository
import com.capgemini.dcx.assisgnment.view.search.SearchViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WeatherApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@WeatherApplication))

        bind() from singleton { NetworkInterceptor(instance()) }
        bind() from singleton { WeatherService(instance()) }
        bind() from singleton { ApplicationRoomDatabase(instance()) }
        bind() from singleton { AppRepository(instance(), instance()) }
        bind() from provider { SearchViewModelFactory(instance()) }
    }
}