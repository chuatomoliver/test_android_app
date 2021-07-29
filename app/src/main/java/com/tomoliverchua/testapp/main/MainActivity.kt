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
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.tomoliverchua.testapp.models.DataResponse
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_nav.view.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mainActViewModel : MainActivityViewModel
    private lateinit var adapter: AirportAdapter
    private val context: Context = this
    private val airportList: MutableList<DataResponse.AirpotDetails> = mutableListOf()

    private lateinit var progressDialog: MaterialDialog
    private lateinit var confirmDialog: MaterialDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        setupDialog()
        subscribeUi()
        initView()
        setupList()
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

        mainActViewModel.getIsLoading().observe(this, Observer<Boolean> { isLoading ->
            isLoading?.let { loading ->
                if (loading) progressDialog.show() else progressDialog.hide()
            }
        })

        mainActViewModel.getDbAirportDetails().observe(this, Observer {
            it.let {
                adapter.updateData(it)
            }
        })
    }
















    private fun setupList() {
        val layoutManager = LinearLayoutManager(this)
        rv_moview_list.layoutManager = layoutManager

        rv_moview_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        adapter = AirportAdapter(mutableListOf(), context)


        // add AddOnScrollListener
        rv_moview_list.adapter = adapter

        val selectMovie = adapter.getSelectedRoute()
        selectMovie?.let {movie ->
            mainActViewModel.getDbMovieById(movie.id!!.toInt()).observe(this, Observer {
                it.let{movieDetailsEntity ->

                }
            })
        }


    }



        private fun setupDialog() {
        context?.let { ctx ->
            progressDialog = MaterialDialog.Builder(ctx)
                .title("Loading")
                .content("Starting your journey please wait")
                .progress(true, 0)
                .cancelable(false)
                .build()
        }
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