package com.example.vishavjit_harika.ui.results

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vishavjit_harika.R

class StressDataTableAdapter(private val stressDataList: List<StressData>) :
    RecyclerView.Adapter<StressDataTableAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
        val stressLevelTextView: TextView = itemView.findViewById(R.id.stressLevelTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.table_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stressData = stressDataList[position]
        holder.timestampTextView.text = stressData.timestamp
        holder.stressLevelTextView.text = stressData.userStress.toString()
    }

    override fun getItemCount(): Int {
        return stressDataList.size
    }
}