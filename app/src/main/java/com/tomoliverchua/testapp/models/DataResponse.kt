package com.tomoliverchua.testapp.models

import com.tomoliverchua.testapp.repositories.AirportDetailRepository


class DataResponse : ArrayList<DataResponse.AirpotDetails>(){
    data class AirpotDetails(
        val airportCode: String = "",
        val airportName: String = "",
        val city: City = City(),
        val country: Country = Country(),
        val domesticAirport: Boolean = false,
        val eticketableAirport: Boolean = false,
        val internationalAirport: Boolean = false,
        val location: Location = Location(),
        val onlineIndicator: Boolean = false,
        val preferredInternationalAirportCode: String = "",
        val region: Region = Region(),
        val regionalAirport: Boolean = false
    ) {
        fun toAirportDetails() : AirpotDetailsEntity{
            return AirpotDetailsEntity(
                airportCode,
                airportName,
                domesticAirport,
                eticketableAirport,
                internationalAirport,
                onlineIndicator,
                preferredInternationalAirportCode,
                regionalAirport,
                country.countryName,
                country.countryCode
            )
        }

        data class City(
            val cityCode: String = "",
            val cityName: String = "",
            val timeZoneName: String = ""
        ){
            fun toCityDetails() : CityEntity{
                return CityEntity(
                    cityCode,
                    cityName,
                    timeZoneName
                )
            }
        }

        data class Country(
            val countryCode: String = "",
            val countryName: String = ""
        ){
            fun toCountry() : CountryEntity{
                return CountryEntity(
                    countryCode,
                    countryName
                )
            }
        }

        data class Location(
            val aboveSeaLevel: Int = 0,
            val latitude: Double = 0.0,
            val latitudeDirection: String = "",
            val latitudeRadius: Double = 0.0,
            val longitude: Double = 0.0,
            val longitudeDirection: String = "",
            val longitudeRadius: Double = 0.0
        ){
            fun toLocation() : LocationEntity{
                return LocationEntity(
                    aboveSeaLevel,
                    latitude,
                    latitudeDirection,
                    latitudeRadius,
                    longitude,
                    longitudeDirection,
                    longitudeRadius
                )
            }
        }

        data class Region(
            val regionCode: String = "",
            val regionName: String = ""
        ) {
            fun toRegion(): RegionEntity {
                return RegionEntity(
                    regionCode,
                    regionName
                )
            }
        }
    }
}