package com.tomoliverchua.testapp.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tomoliverchua.testapp.db.dao.*
import com.tomoliverchua.testapp.models.*
import com.tomoliverchua.testapp.repositories.DetailsActivityRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class DetailsActivityViewModel: ViewModel(), KoinComponent {

    private val detailsActivityRepository : DetailsActivityRepository by inject()


    // get movie by ref. Id
    fun getDBCity(Id: Int?): LiveData<CityEntity> = detailsActivityRepository.getCity(Id)

    // get movie by ref. Id
    fun getDCountryDetails(Id: Int?): LiveData<CountryEntity> = detailsActivityRepository.getCountry(Id)

    // get movie by ref. Id
    fun getDBLocation(Id: Int?): LiveData<LocationEntity> = detailsActivityRepository.getLocation(Id)

    // get movie by ref. Id
    fun getDBRegion(Id: Int?): LiveData<RegionEntity> = detailsActivityRepository.getRegion(Id)
}