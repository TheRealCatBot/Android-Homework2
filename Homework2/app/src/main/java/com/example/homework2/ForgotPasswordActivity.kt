package com.example.homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class ForgotPasswordActivity : AppCompatActivity() {


    private lateinit var recoveryButton : Button
    private lateinit var recoveryEmail : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        recoveryButton = findViewById(R.id.forgotButton)
        recoveryEmail = findViewById(R.id.forgotEmailEditText)

        recoveryButton.setOnClickListener {
            val email = recoveryEmail.text.toString()
            if(email.isEmpty()) return@setOnClickListener

            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener{
                if(it.isSuccessful){
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}