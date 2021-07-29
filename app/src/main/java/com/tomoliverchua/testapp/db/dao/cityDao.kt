package com.tomoliverchua.testapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tomoliverchua.testapp.models.AirpotDetailsEntity
import com.tomoliverchua.testapp.models.CityEntity
@Dao
interface cityDao {
    @Query("SELECT * FROM tbl_city")
    fun getCityDetails(): LiveData<MutableList<CityEntity>>

    @Query("SELECT * FROM tbl_city where id = :AirportId ")
    fun getCityDetailsById(AirportId : Int): LiveData<CityEntity>

    @Insert
    fun saveCityDetails(cityEntity: CityEntity)

    @Update
    fun updateCityDetails(cityEntity: CityEntity)

    @Query("DELETE FROM tbl_city")
    fun clearCityDetails()
}