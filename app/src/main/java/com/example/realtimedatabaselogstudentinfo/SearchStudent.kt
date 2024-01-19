package com.example.realtimedatabaselogstudentinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabaselogstudentinfo.databinding.ActivityMainBinding
import com.example.realtimedatabaselogstudentinfo.databinding.ActivitySearchStudentBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SearchStudent : AppCompatActivity() {
    val binding by lazy {
        ActivitySearchStudentBinding.inflate(layoutInflater)
    }
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            val name = binding.namesearc.text.toString()
            val phone = binding.phoneEtxt.text.toString()

            if(name.isNotEmpty() && phone.isNotEmpty()){
                getData(name, phone)
            }
            else{
                Toast.makeText(this@SearchStudent,"Please, Fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getData(name: String, phone: String) {
        database = FirebaseDatabase.getInstance().getReference("Students")
        database.child(name+phone).get()
            .addOnSuccessListener {
                if(it.exists()){
                    val batch = it.child("batch").value
                    val name = it.child("name").value
                    val email = it.child("email").value
                    val phone = it.child("phone").value
                    val subject = it.child("subject").value

                    binding.batchTxt.text = batch.toString()
                    binding.nameTxt.text = name.toString()
                    binding.emailTx.text = email.toString()
                    binding.phoneTxt.text = phone.toString()
                    binding.subjectTxt.text = subject.toString()

                    binding.namesearc.text?.clear()
                    binding.phoneEtxt.text?.clear()
                    Toast.makeText(this@SearchStudent,"Found", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this@SearchStudent,"Not Found", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this@SearchStudent,"Failed", Toast.LENGTH_SHORT).show()
            }
    }
}