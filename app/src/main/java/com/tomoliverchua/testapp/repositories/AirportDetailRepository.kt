package com.tomoliverchua.testapp.repositories

import android.content.Context
import android.util.Log
import com.tomoliverchua.testapp.apiservices.AirportService
import com.tomoliverchua.testapp.apiservices.APIClient
import com.tomoliverchua.testapp.models.DataResponse
import com.tomoliverchua.testapp.repoCallback.AiportDetailsCallback
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AirportDetailRepository(context: Context) : KoinComponent {


        var apiServices: AirportService = APIClient.client!!.create(AirportService::class.java)



//        // get movies from api to room database
//
        fun getAirportDetails(callback: AiportDetailsCallback.OnAirportDetailsCallback) {
            val getAirportDetails = apiServices.getAiportDetails()
            getAirportDetails.enqueue(object : Callback<DataResponse> {
                override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                    if(response.isSuccessful){
                        response.body()?.let {
                            callback.onSuccess(it)
                            Log.d("test","$it")
                        }
                    }
                }

                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d("API_CALL", t.message)
                    callback.onFail("Connection Error!")
                }

            })
        }
//
//        // get all movie details
//        fun getDbMovies(): LiveData<MutableList<MovieDetailsEntity>> = movieDao.getMovieList()
//
//
//        // get movie by ref. Id
//        fun getDbMovieById(movieId: Int): LiveData<MovieDetailsEntity> = movieDao.getMovieById(movieId)

}