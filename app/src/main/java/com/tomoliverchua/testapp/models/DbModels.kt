package com.tomoliverchua.testapp.models

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "tbl_airport_details")
data class AirpotDetailsEntity(
    @ColumnInfo(name = "airportCode") val airportCode: String = "",
    @ColumnInfo(name = "airportName") val airportName: String = "",
    @ColumnInfo(name = "domesticAirport") val domesticAirport: Boolean = false,
    @ColumnInfo(name = "eticketableAirport")val eticketableAirport: Boolean = false,
    @ColumnInfo(name = "internationalAirport")val internationalAirport: Boolean = false,
    @ColumnInfo(name = "onlineIndicator")val onlineIndicator: Boolean = false,
    @ColumnInfo(name = "preferredInternationalAirportCode")val preferredInternationalAirportCode: String = "",
    @ColumnInfo(name = "regionalAirport") val regionalAirport: Boolean = false,
    @ColumnInfo(name = "countryCode") val countryCode: String = "",
    @ColumnInfo(name = "countryName") val countryName: String = ""
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

@Entity(tableName = "tbl_city")
data class CityEntity(
    @ColumnInfo(name = "cityCode") val cityCode: String = "",
    @ColumnInfo(name = "cityName")val cityName: String = "",
    @ColumnInfo(name = "timeZoneName")val timeZoneName: String = ""
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

@Entity(tableName = "tbl_country")
data class CountryEntity(
    @ColumnInfo(name = "countryCode") val countryCode: String = "",
    @ColumnInfo(name = "countryName") val countryName: String = ""
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

@Entity(tableName = "tbl_location")
data class LocationEntity(
    @ColumnInfo(name = "aboveSeaLevel")val aboveSeaLevel: Int = 0,
    @ColumnInfo(name = "latitude")val latitude: Double = 0.0,
    @ColumnInfo(name = "latitudeDirection")val latitudeDirection: String = "",
    @ColumnInfo(name = "latitudeRadius")val latitudeRadius: Double = 0.0,
    @ColumnInfo(name = "longitude")val longitude: Double = 0.0,
    @ColumnInfo(name = "longitudeDirection")val longitudeDirection: String = "",
    @ColumnInfo(name = "longitudeRadius")val longitudeRadius: Double = 0.0
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}

@Entity(tableName = "tbl_region")
data class RegionEntity(
    @ColumnInfo(name = "regionCode") val regionCode: String = "",
    @ColumnInfo(name = "regionName") val regionName: String = ""
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}





