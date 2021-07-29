package com.tomoliverchua.testapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tomoliverchua.testapp.models.CountryEntity
import androidx.room.Dao
import androidx.room.Insert

@Dao
interface countryDao {
    @Query("SELECT * FROM tbl_country")
    fun getCountryDetails(): LiveData<MutableList<CountryEntity>>

    @Query("SELECT * FROM tbl_country where id = :AirportId ")
    fun getCountryDetailsById(AirportId : Int): LiveData<CountryEntity>

    @Insert
    fun saveCountryDetails(countryEntity: CountryEntity)

    @Update
    fun updateCountryDetails(countryEntity: CountryEntity)

    @Query("DELETE FROM tbl_country")
    fun clearCountryDetails()
}