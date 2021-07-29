package com.tomoliverchua.testapp.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tomoliverchua.testapp.models.DataResponse
import com.tomoliverchua.testapp.repoCallback.AiportDetailsCallback
import com.tomoliverchua.testapp.repositories.AirportDetailRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainActivityViewModel: ViewModel(),KoinComponent {

    private val airportRepo : AirportDetailRepository by inject()

    private var dataResponse = MutableLiveData<ArrayList<DataResponse.AirpotDetails>>()
    fun getDataResponse(): LiveData<ArrayList<DataResponse.AirpotDetails>> = dataResponse

    fun getAirportDetails() {
        airportRepo.getAirportDetails(object : AiportDetailsCallback.OnAirportDetailsCallback {
            override fun onSuccess(AiportDetails: DataResponse) {
                dataResponse.value = AiportDetails
            }
            override fun onFail(errMessage: String) {
                Log.d("test","error")
            }
        })
    }
}