package com.tomoliverchua.testapp.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.tomoliverchua.testapp.common.AppExecutors
import com.tomoliverchua.testapp.db.dao.*
import com.tomoliverchua.testapp.models.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailsActivityRepository(context: Context) : KoinComponent {
    private val AirportDetailsDao: airportDetailsDao by inject()
    private val CityDao: cityDao by inject ()
    private val CountryDao: countryDao by inject ()
    private val LocationDao: locationDao by inject ()
    private val RegionDao: regionDao by inject()

    private val executors: AppExecutors by inject()
    // get movie by ref. Id

        fun getCity(Id: Int?): LiveData<CityEntity> = CityDao.getCityDetailsById(Id)
        fun getCountry(Id: Int?): LiveData<CountryEntity> = CountryDao.getCountryDetailsById(Id)
        fun getLocation(Id: Int?): LiveData<LocationEntity> = LocationDao.getLocationDetailsById(Id)
        fun getRegion(Id: Int?): LiveData<RegionEntity> = RegionDao.getRegionDetailsById(Id)


}