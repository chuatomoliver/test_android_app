package com.tomoliverchua.testapp.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.tomoliverchua.testapp.apiservices.AirportService
import com.tomoliverchua.testapp.apiservices.APIClient
import com.tomoliverchua.testapp.utils.AppExecutors
import com.tomoliverchua.testapp.db.dao.*
import com.tomoliverchua.testapp.models.AirpotDetailsEntity
import com.tomoliverchua.testapp.models.DataResponse
import com.tomoliverchua.testapp.repoCallback.AiportDetailsCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AirportDetailRepository(context: Context) : KoinComponent {

        var apiServices: AirportService = APIClient.client!!.create(AirportService::class.java)

        private val AirportDetailsDao: airportDetailsDao by inject()
        private val CityDao: cityDao by inject ()
        private val CountryDao: countryDao by inject ()
        private val LocationDao: locationDao by inject ()
        private val RegionDao: regionDao by inject()

        private val executors: AppExecutors by inject()


//        // get movies from api to room database
//
        fun getAirportDetails(callback: AiportDetailsCallback.OnAirportDetailsCallback) {
            val getAirportDetails = apiServices.getAiportDetails()
            getAirportDetails.enqueue(object : Callback<DataResponse> {
                override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onSuccess(it)
                            it.forEach{result->
//                                    executors.diskIO().execute {
                                    CoroutineScope(IO).launch {
                                        AirportDetailsDao.saveAirportDetails(result.toAirportDetails())
                                        CityDao.saveCityDetails(result.city.toCityDetails())
                                        LocationDao.saveLocationDetails(result.location.toLocation())
                                        RegionDao.saveRegionDetails(result.region.toRegion())
                                        CountryDao.saveCountryDetails(result.country.toCountry())
                                    }

//                                }
                            }
                        }
                        Log.d("API_CALL", response.body().toString())
                        return
                    }
                }
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d("API_CALL", t.message)
                    callback.onFail("Connection Error!")
                }

            })
        }

//        fun startAirportDetails(callback: AiportDetailsCallback.OnUpdateAirportDetailsStatusCallback) {
//            executors.diskIO().execute {
//            val trip = tripDao.getCurrentTrip()
//            trip.status = TripStatus.IN_TRANSIT
//            updateTrip(trip, callback)
//         }
//        }



        // get all movie details
        fun getDBAirportDetails(): LiveData<MutableList<AirpotDetailsEntity>> = AirportDetailsDao.getAirportDetails()


        // get movie by ref. Id
        fun getDBAirportDetailsById(movieId: Int): LiveData<AirpotDetailsEntity> = AirportDetailsDao.getAirportDetailsById(movieId)

}