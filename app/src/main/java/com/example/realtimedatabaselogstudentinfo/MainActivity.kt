package com.example.realtimedatabaselogstudentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realtimedatabaselogstudentinfo.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbRef: DatabaseReference
    private lateinit var studentList:ArrayList<Student>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutBtn.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser!=null){
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(
                    this,LoginActivity::class.java
                )
                startActivity(intent)
                finishAffinity()
            }

        }

        binding.addBtn.setOnClickListener {
            val intent = Intent(
                this,AddActivity::class.java)
            startActivity(intent)
        }

        studentList = arrayListOf()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)
        fetchData()

        binding.updateBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, UpdateActivity::class.java))
            finish()
        }

        binding.deleteBtn.setOnClickListener {
            startActivity(Intent(this@MainActivity, DeleteActivity::class.java))
            finish()
        }

        binding.btsearch.setOnClickListener {
            startActivity(Intent(this@MainActivity, SearchStudent::class.java))
            finish()
        }


    }
    private fun fetchData(){

        dbRef = FirebaseDatabase.getInstance().getReference("students")
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapShot in snapshot.children){
                        val student = dataSnapShot.getValue(Student::class.java)
                        studentList.add(student!!)
                    }
                    val studentAdapter = StudentAdapter(studentList)
                    binding.recyclerView.adapter = studentAdapter
                    studentAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}