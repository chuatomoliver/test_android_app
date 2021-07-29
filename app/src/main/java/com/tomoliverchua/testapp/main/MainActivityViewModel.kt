package com.tomoliverchua.testapp.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomoliverchua.testapp.models.AirpotDetailsEntity
import com.tomoliverchua.testapp.models.DataResponse
import com.tomoliverchua.testapp.repoCallback.AiportDetailsCallback
import com.tomoliverchua.testapp.repositories.AirportDetailRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityViewModel: ViewModel(),KoinComponent {

    private val airportRepo : AirportDetailRepository by inject()

    private val isLoading = MutableLiveData<Boolean>()
    fun getIsLoading(): LiveData<Boolean> = isLoading

    private var dataResponse = MutableLiveData<ArrayList<DataResponse.AirpotDetails>>()
    fun getDataResponse(): LiveData<ArrayList<DataResponse.AirpotDetails>> = dataResponse

    fun getAirportDetails() {
        isLoading.postValue(true)
        airportRepo.getAirportDetails(object : AiportDetailsCallback.OnAirportDetailsCallback {
            override fun onSuccess(AiportDetails: DataResponse) {
                dataResponse.value = AiportDetails
                isLoading.postValue(false)
            }
            override fun onFail(errMessage: String) {
                Log.d("test","error")
                isLoading.postValue(true)
            }
        })
    }


    fun startTrip() {
        isLoading.value = true

    }

    fun getDbAirportDetails() : LiveData<MutableList<AirpotDetailsEntity>> = airportRepo.getDBAirportDetails()
    fun getDbMovieById(airportId: Int) : LiveData<AirpotDetailsEntity> = airportRepo.getDBAirportDetailsById(airportId)


}