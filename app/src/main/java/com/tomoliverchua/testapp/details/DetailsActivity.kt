package com.tomoliverchua.testapp.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tomoliverchua.testapp.R
import com.tomoliverchua.testapp.common.AIRPORT_DETAILS
import com.tomoliverchua.testapp.models.AirpotDetailsEntity
import kotlinx.android.synthetic.main.activity_movie_details.*

class DetailsActivity : AppCompatActivity() {

    var airportDetailsEntity: AirpotDetailsEntity? = null
    private lateinit var detailsActivityViewModel: DetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)


        detailsActivityViewModel = ViewModelProviders.of(this)[DetailsActivityViewModel::class.java]
        // get data from mainActivity
        airportDetailsEntity = intent.getSerializableExtra(AIRPORT_DETAILS) as? AirpotDetailsEntity
        subscribeUi()
    }


    // for livedata
    private fun subscribeUi() {
        Log.d("ters123", "${airportDetailsEntity?.airportCode} ")

        airportDetailsEntity.let {
            data1.text = airportDetailsEntity?.airportCode
            data2.text = airportDetailsEntity?.airportName
        }

        detailsActivityViewModel.getDBCity(airportDetailsEntity?.id?.toInt()).observe(this, Observer {
            it.let {
                data3.text = it.cityName
                data4.text = it.cityCode
            }
        })

        detailsActivityViewModel.getDCountryDetails(airportDetailsEntity?.id?.toInt()).observe(this, Observer {
            it.let {
                data5.text = it.countryCode
                data6.text = it.countryName
            }
        })

        detailsActivityViewModel.getDBLocation(airportDetailsEntity?.id?.toInt()).observe(this, Observer {
            it.let {
                data7.text = it.latitudeDirection
                data8.text = it.longitudeDirection
                data9.text = it.latitude.toString()
                data10.text = it.longitude.toString()
            }
        })

        detailsActivityViewModel.getDBRegion(airportDetailsEntity?.id?.toInt()).observe(this, Observer {
            it.let {
                data11.text = it.regionCode
                data12.text = it.regionName
            }
        })

        btnBack.setOnClickListener {
            this.finish()
        }
    }


}
