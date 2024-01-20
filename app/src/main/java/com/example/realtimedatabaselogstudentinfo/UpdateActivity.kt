package com.example.realtimedatabaselogstudentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabaselogstudentinfo.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityUpdateBinding.inflate(layoutInflater)
    }
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backbt.setOnClickListener {
            startActivity(Intent(this@UpdateActivity, MainActivity::class.java))
            finish()
        }

        binding.updateBtn.setOnClickListener {
            val batch = binding.batchEtxt.text.toString()
            val name = binding.nameEtxt.text.toString()
            val email = binding.emailEtxt.text.toString()
            val phone = binding.phoneEtxt.text.toString()
            val subject = binding.subjectEtxt.text.toString()
            updateData(batch, name, email, phone, subject)
        }
    }

    private fun updateData(batch: String, name: String, email: String, phone: String, subject: String) {
        database = FirebaseDatabase.getInstance().getReference("Students")

        val student = mapOf<String, String>(
            "batch" to batch,
            "name" to name,
            "email" to email,
            "phone" to phone,
            "subject" to subject,
        )

        database.child(name+phone).updateChildren(student)
            .addOnSuccessListener {
                binding.batchEtxt.text?.clear()
                binding.nameEtxt.text?.clear()
                binding.emailEtxt.text?.clear()
                binding.phoneEtxt.text?.clear()
                binding.subjectEtxt.text?.clear()
                Toast.makeText(this@UpdateActivity,"Updated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@UpdateActivity, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this@UpdateActivity,"Failed", Toast.LENGTH_SHORT).show()
            }

    }
}