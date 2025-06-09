package com.example.conversordemoedas.model

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResourcesAdapter(private val resourcesList: List<String>) :
    RecyclerView.Adapter<ResourcesAdapter.ResourcesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourcesViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item_resource, parent, false)
        return ResourcesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResourcesViewHolder, position: Int) {

        val resource = resourcesList[position]
        holder.tvResource.text = resource
    }

    override fun getItemCount(): Int {
        return resourcesList.size
    }


    class ResourcesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvResource: TextView = itemView.findViewById(R.id.tv_resource)
    }
}