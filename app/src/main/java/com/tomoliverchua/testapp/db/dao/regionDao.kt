package com.tomoliverchua.testapp.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.tomoliverchua.testapp.models.CityEntity
import com.tomoliverchua.testapp.models.RegionEntity
@Dao
interface regionDao {
    @Query("SELECT * FROM tbl_region")
    fun getRegionDetails(): LiveData<MutableList<RegionEntity>>

    @Query("SELECT * FROM tbl_region where id = :AirportId ")
    fun getRegionDetailsById(AirportId : Int): LiveData<RegionEntity>

    @Insert
    fun saveRegionDetails(regionEntity: RegionEntity)

    @Update
    fun updateRegionDetails(regionEntity: RegionEntity)

    @Query("DELETE FROM tbl_city")
    fun clearCityDetails()
}