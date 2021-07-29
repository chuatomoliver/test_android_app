package com.tomoliverchua.testapp.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tomoliverchua.testapp.R
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tomoliverchua.testapp.models.DataResponse
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mainActViewModel : MainActivityViewModel
    private val context: Context = this


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        subscribeUi()
    }

    fun setDate(){
        val sdf = SimpleDateFormat("dd/MM/yyyy ")
        val currentDate = sdf.format(Date())
//        tv_date.text = currentDate
    }

    // for livedata
    private fun subscribeUi() {
        mainActViewModel.getAirportDetails()

        mainActViewModel.getDataResponse().observe(this, Observer { airportDetailsList ->
            airportDetailsList?.let{
                for (airportResult in airportDetailsList){

                }
            }

        })

    }
}