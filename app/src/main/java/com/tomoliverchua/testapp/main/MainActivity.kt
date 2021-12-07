package com.tomoliverchua.testapp.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import com.tomoliverchua.testapp.R
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.navigation.NavigationView
import com.tomoliverchua.testapp.utils.AIRPORT_DETAILS
import com.tomoliverchua.testapp.details.DetailsActivity
import com.tomoliverchua.testapp.models.AirpotDetailsEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_nav.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    OnSelectRouteCallback {

    private lateinit var mainActViewModel: MainActivityViewModel
    private lateinit var adapter: AirportAdapter
    private val context: Context = this

    private lateinit var progressDialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActViewModel = ViewModelProviders.of(this)[MainActivityViewModel::class.java]

        setupDialog()
        initView()
        setupList()
        subscribeUi()
    }


    // for livedata
    private fun subscribeUi() {
        mainActViewModel.getAirportDetails()

        mainActViewModel.getIsLoading().observe(this, Observer<Boolean> { isLoading ->
            isLoading?.let { loading ->
                if (loading) progressDialog.show() else progressDialog.hide()
            }
        })

        mainActViewModel.getDbAirportDetails().observe(this, Observer {
            it.let {
                adapter.submitList(it)
            }
        })
    }


    private fun setupList() {
        val layoutManager = LinearLayoutManager(this)
        rv_airport_list.layoutManager = layoutManager

        rv_airport_list.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = AirportAdapter( this)


        // add AddOnScrollListener
        rv_airport_list.adapter = adapter

    }


    private fun setupDialog() {
        context.let { ctx ->
            progressDialog = MaterialDialog.Builder(ctx)
                .title("Loading")
                .content("Starting your journey please wait")
                .progress(true, 0)
                .cancelable(false)
                .build()
        }
    }

    // for toolbar
    fun initView() {
        toolbar.navigation_menu.setOnClickListener {
            drawer_layout.openDrawer()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        drawer_layout.closeDrawer(Gravity.START)
        return false
    }

    override fun onSelect(route: AirpotDetailsEntity) {
        var intent = Intent(context, DetailsActivity::class.java)
        intent.putExtra(AIRPORT_DETAILS, route)
        context.startActivity(intent)
    }
}