package com.tomoliverchua.testapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tomoliverchua.testapp.models.AirpotDetailsEntity


@Dao
interface airportDetailsDao {

    @Query("SELECT * FROM tbl_airport_details")
    fun getAirportDetails(): LiveData<MutableList<AirpotDetailsEntity>>


    @Query("SELECT * FROM tbl_airport_details where id = :AirportId ")
    fun getAirportDetailsById(AirportId : Int?): LiveData<AirpotDetailsEntity>

    @Insert
    fun saveAirportDetails(airportDetails: AirpotDetailsEntity)

    @Update
    fun updateAirportDetails(airportDetails: AirpotDetailsEntity)

    @Query("DELETE FROM tbl_airport_details")
    fun clearAllAirportDetails()
}