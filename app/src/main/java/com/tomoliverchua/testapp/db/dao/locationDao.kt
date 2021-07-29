package com.tomoliverchua.testapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tomoliverchua.testapp.models.LocationEntity
@Dao
interface locationDao {
    @Query("SELECT * FROM tbl_location")
    fun getLocationDetails(): LiveData<MutableList<LocationEntity>>

    @Query("SELECT * FROM tbl_location where id = :AirportId ")
    fun getLocationDetailsById(AirportId : Int): LiveData<LocationEntity>

    @Insert
    fun saveLocationDetails(locationEntity: LocationEntity)

    @Update
    fun updateLocationDetails(locationEntity: LocationEntity)

    @Query("DELETE FROM tbl_location")
    fun clearLocationEntity()
}