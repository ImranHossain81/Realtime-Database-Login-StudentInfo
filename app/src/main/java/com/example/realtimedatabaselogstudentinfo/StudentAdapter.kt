package com.example.realtimedatabaselogstudentinfo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter (private val studentList: ArrayList<Student>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_ltemlist,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val student : Student = studentList[position]

        holder.sBatch.text = student.batch
        holder.sName.text = student.name
        holder.sEmail.text = student.email
        holder.sPhone.text = student.phone
        holder.sSubject.text = student.subject
    }

}
