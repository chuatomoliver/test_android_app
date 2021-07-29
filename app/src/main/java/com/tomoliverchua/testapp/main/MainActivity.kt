package com.tomoliverchua.testapp.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import com.tomoliverchua.testapp.R
import java.text.SimpleDateFormat
import java.util.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tomoliverchua.testapp.models.DataResponse
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_nav.view.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainActViewModel : MainActivityViewModel
    private val context: Context = this
    private val airportList: MutableList<DataResponse.AirpotDetails> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        subscribeUi()
        initView()
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
                it.toList()
                for (airportResult in airportDetailsList){
                    airportList.add(airportResult)
                }
            }
        })
    }

    // for toolbar
    fun initView(){
        toolbar.navigation_menu.setOnClickListener{
            drawer_layout.openDrawer()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        drawer_layout.closeDrawer(Gravity.START)
        return false
    }
}