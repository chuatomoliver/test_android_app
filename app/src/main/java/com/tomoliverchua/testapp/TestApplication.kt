package com.tomoliverchua.testapp

import android.app.Application
import com.tomoliverchua.testapp.utils.AppExecutors
import com.tomoliverchua.testapp.repositories.AirportDetailRepository
import com.tomoliverchua.testapp.repositories.DetailsActivityRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

class TestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        val modules = module {
            single(named("appDatabase")) { AppDatabase.getInstance(get()) }
            single { AirportDetailRepository(get()) }
            single { DetailsActivityRepository(get()) }

            single { AppDatabase.getInstance(get()).airportDetailsDao() }
            single { AppDatabase.getInstance(get()).cityDao() }
            single { AppDatabase.getInstance(get()).countryDao() }
            single { AppDatabase.getInstance(get()).locationDao() }
            single { AppDatabase.getInstance(get()).regionDao() }

            single { AppExecutors() }
        }

        startKoin {
            androidContext(this@TestApplication)
            modules(modules)
        }
    }
}