package com.example.realtimedatabaselogstudentinfo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val sBatch = view.findViewById<TextView>(R.id.batch)
    val sName = view.findViewById<TextView>(R.id.name)
    val sEmail = view.findViewById<TextView>(R.id.email)
    val sPhone = view.findViewById<TextView>(R.id.phone)
    val sSubject = view.findViewById<TextView>(R.id.subject)
}