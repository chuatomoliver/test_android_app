package com.tomoliverchua.testapp.apiservices

import com.tomoliverchua.testapp.common.API_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private var retrofit: Retrofit? = null

    val client: Retrofit?
        get() {

            retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit
        }
}