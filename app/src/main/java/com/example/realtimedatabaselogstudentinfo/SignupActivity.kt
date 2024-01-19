package com.example.realtimedatabaselogstudentinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabaselogstudentinfo.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.SignupBtn.setOnClickListener {
            signUp()
        }
        binding.goLogin.setOnClickListener {
            val intent = Intent(
                this,LoginActivity::class.java
            )
            startActivity(intent)
            finishAffinity()
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
    private fun signUp(){
        val email = binding.etEmail.text.toString()
        val pass = binding.enterPass.text.toString()
        val conPass = binding.confPassword.text.toString()


        if (pass!=conPass){
            Toast.makeText(this, "Password and Confirm Password should be same.", Toast.LENGTH_SHORT).show()
        }
        if (email.isNotEmpty() && pass.isNotEmpty() && conPass.isNotEmpty()){
            firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(this, "Signup successful.", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, "Signup failed.", Toast.LENGTH_SHORT).show()
        }

    }
}