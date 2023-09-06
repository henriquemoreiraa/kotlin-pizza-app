package com.example.pizzaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pizzaapp.R
import com.example.pizzaapp.SummaryFragment

class AdditionalAdapter(private val context: SummaryFragment, private val dataset: List<String>) :
    RecyclerView.Adapter<AdditionalAdapter.AdditionalViewHolder>() {
    class AdditionalViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val additionalName: TextView = view.findViewById(R.id.additional_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_additional, parent, false)

        return AdditionalViewHolder(adapterLayout)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: AdditionalViewHolder, position: Int) {
        val additional = dataset[position]

        holder.additionalName.text = additional
    }
}