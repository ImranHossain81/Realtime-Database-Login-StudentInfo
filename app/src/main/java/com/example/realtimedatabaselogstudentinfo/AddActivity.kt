package com.example.realtimedatabaselogstudentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabaselogstudentinfo.databinding.ActivityAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var dbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            startActivity(Intent(this@AddActivity, MainActivity::class.java))
            finish()
        }

        binding.saveBtn.setOnClickListener {
            addStudent() }

    }
    private fun addStudent(){
        val batch = binding.batchEtxt.text.toString()
        val name = binding.nameEtxt.text.toString()
        val email = binding.emailEtxt.text.toString()
        val phone = binding.phoneEtxt.text.toString()
        val subject = binding.subjectEtxt.text.toString()

        if (batch.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && subject.isNotEmpty()){
            dbRef = FirebaseDatabase.getInstance().getReference("students")
            val student = Student(
                batch, name, email, phone, subject
            )
            dbRef.child("$name + $phone")
                .setValue(student)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Student added successful.", Toast.LENGTH_SHORT).show()
                        startActivity(
                            Intent(
                            this,MainActivity::class.java
                        )
                        )
                        finishAffinity()
                    }
                    else{
                        Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }
        else{
            Toast.makeText(this, "Failed to add student.", Toast.LENGTH_SHORT).show()
        }




    }
}