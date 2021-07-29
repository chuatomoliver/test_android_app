package com.tomoliverchua.testapp

import android.app.Application
import com.tomoliverchua.testapp.common.AppExecutors
import com.tomoliverchua.testapp.repositories.AirportDetailRepository
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
            single { AppDatabase.getInstance(get()).airportDetailsDao() }
            single { AppExecutors() }
        }

        startKoin {
            androidContext(this@TestApplication)
            modules(modules)
        }
    }
}