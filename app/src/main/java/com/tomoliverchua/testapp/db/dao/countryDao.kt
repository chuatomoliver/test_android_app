package com.tomoliverchua.testapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tomoliverchua.testapp.models.CountryEntity

interface countryDao {
    @Query("SELECT * FROM tbl_country")
    fun getCountryDetails(): LiveData<MutableList<countryDao>>

    @Query("SELECT * FROM tbl_country where id = :AirportId ")
    fun getCountryDetailsById(AirportId : Int): LiveData<countryDao>

    @Insert
    fun saveCountryDetails(countryEntity: CountryEntity)

    @Update
    fun updateCountryDetails(countryEntity: CountryEntity)

    @Query("DELETE FROM tbl_country")
    fun clearCountryDetails()
}