package com.tomoliverchua.testapp.main

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.tomoliverchua.testapp.R
import com.tomoliverchua.testapp.models.AirpotDetailsEntity
import com.tomoliverchua.testapp.repositories.AirportDetailRepository
import kotlinx.android.synthetic.main.listview_item.view.*

class AirportAdapter(
    var selectRouteCallback: OnSelectRouteCallback? = null
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedPosition = -1

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AirpotDetailsEntity>() {

        override fun areItemsTheSame(
            oldItem: AirpotDetailsEntity,
            newItem: AirpotDetailsEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: AirpotDetailsEntity,
            newItem: AirpotDetailsEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return AirportViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.listview_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AirportViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<AirpotDetailsEntity>) {
        differ.submitList(list)
    }

    inner class AirportViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

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
            }
            else{
                rootContainer.setBackgroundResource(R.drawable.cardview_bg)
            }

            rootContainer.setOnClickListener {
                Log.d("test123", "Click data : ${airpotDetailsEntity}")
                selectedPosition = adapterPosition
                notifyDataSetChanged()
                selectRouteCallback?.onSelect(airpotDetailsEntity)
            }
        }
    }
}

interface OnSelectRouteCallback {
    fun onSelect(route: AirpotDetailsEntity)
}
