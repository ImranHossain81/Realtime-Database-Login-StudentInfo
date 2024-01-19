package com.example.realtimedatabaselogstudentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabaselogstudentinfo.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.loginBt.setOnClickListener {
            login()
        }
        binding.goRegister.setOnClickListener {
            val intent = Intent(
                this,SignupActivity::class.java
            )
            startActivity(intent)
            finishAffinity()
        }

    }
    private fun login(){
        val email = binding.logemail.text.toString()
        val pass = binding.logPass.text.toString()
        if (email.isNotEmpty() && pass.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show()
                        val intent = Intent(
                            this,MainActivity::class.java
                        )
                        startActivity(intent)
                        finishAffinity()
                    }
                    else{
                        Toast.makeText(this, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }
        else{
            Toast.makeText(this, "Login failed.", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser!=null){
            val intent = Intent(
                this,MainActivity::class.java
            )
            startActivity(intent)
            finishAffinity()
        }
    }
}