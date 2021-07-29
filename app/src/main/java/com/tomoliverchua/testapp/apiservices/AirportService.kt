package com.tomoliverchua.testapp.apiservices

import com.tomoliverchua.testapp.models.DataResponse
import retrofit2.Call
import retrofit2.http.GET

interface AirportService {
    @GET("flight/refData/airport")
    fun getAiportDetails(): Call<DataResponse>
}