package com.tomoliverchua.testapp.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tomoliverchua.testapp.R
import com.tomoliverchua.testapp.common.AIRPORT_DETAILS
import com.tomoliverchua.testapp.details.DetailsActivity
import com.tomoliverchua.testapp.models.AirpotDetailsEntity
import com.tomoliverchua.testapp.repoCallback.AiportDetailsCallback
import kotlinx.android.synthetic.main.listview_item.view.*

class AirportAdapter (private val routes: MutableList<AirpotDetailsEntity>, context: Context) :
    RecyclerView.Adapter<AirportAdapter.ViewHolder>() {

    private var selectRouteCallback: OnSelectRouteCallback? = null
    private var selectedPosition = -1
    private var context: Context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listview_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = routes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(routes[position])
    }

//    fun updateData(newDataSet: List<MovieDetailsEntity>) {
//        routes.clear()
//        routes.addAll(newDataSet)
//        notifyDataSetChanged()
//    }
//
//    fun addData(newDataSet: List<MovieDetailsEntity>){
//        routes.addAll(newDataSet)
//        notifyDataSetChanged()
//    }

    fun updateData(newDataSet: List<AirpotDetailsEntity>) {
        routes.clear()
        routes.addAll(newDataSet)
        notifyDataSetChanged()
    }

    fun setSelectRouteCallback(callback: OnSelectRouteCallback) {
        selectRouteCallback = callback
    }

    fun getSelectedRoute(): AirpotDetailsEntity? {
        if (selectedPosition < 0) return null
        return routes[selectedPosition]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rootContainer: LinearLayout = itemView.rootContainer
        private val AirportName: TextView = itemView.tvAirportName
        private val itemId: TextView = itemView.tv_id
        private val Country: TextView = itemView.tvCountry

        fun bind(airpotDetailsEntity: AirpotDetailsEntity) {

            AirportName.text = airpotDetailsEntity.airportName
            Country.text = airpotDetailsEntity.countryName
            itemId.text = airpotDetailsEntity.id.toString()

            // If the item is selected update its image source
            if (selectedPosition == adapterPosition) {
                rootContainer.setBackgroundResource(R.drawable.cardview_selected_bg)

                var intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(AIRPORT_DETAILS, airpotDetailsEntity)
                context.startActivity(intent)

            }
            else{
                rootContainer.setBackgroundResource(R.drawable.cardview_bg)
            }

            rootContainer.setOnClickListener {
                selectedPosition = adapterPosition
                notifyDataSetChanged()

                selectRouteCallback?.onSelect(AirpotDetailsEntity())
            }
        }

    }

}

interface OnSelectRouteCallback {
    fun onSelect(route: AirpotDetailsEntity)
}