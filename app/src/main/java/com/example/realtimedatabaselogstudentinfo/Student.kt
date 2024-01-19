package com.example.realtimedatabaselogstudentinfo

import androidx.test.services.events.TimeStamp

data class Student(
    val batch:String?=null,
    val name:String?=null,
    val email:String?=null,
    val phone:String?=null,
    val subject:String?=null,
    val timestamp: TimeStamp?=null

)
