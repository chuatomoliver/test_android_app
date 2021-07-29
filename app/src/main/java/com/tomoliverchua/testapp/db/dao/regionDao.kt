package com.tomoliverchua.testapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tomoliverchua.testapp.models.CityEntity

interface regionDao {
    @Query("SELECT * FROM tbl_region")
    fun getRegionDetails(): LiveData<MutableList<regionDao>>

    @Query("SELECT * FROM tbl_region where id = :AirportId ")
    fun getAirportDetailsById(AirportId : Int): LiveData<regionDao>

    @Insert
    fun saveCityDetails(cityEntity: CityEntity)

    @Update
    fun updateCityDetails(cityEntity: CityEntity)

    @Query("DELETE FROM tbl_city")
    fun clearCityDetails()
}