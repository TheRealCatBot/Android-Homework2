package com.example.homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private lateinit var linkEditText : EditText
    private lateinit var usernameEditText : EditText
    private lateinit var editProfileButton : Button
    private lateinit var userNameTextView : TextView
    private lateinit var imageView : ImageView
    private lateinit var signOutButton : Button
    private lateinit var updatePasswordTextView : TextView
    private lateinit var profileUpdateButton : Button
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseDatabase.getInstance().getReference("User")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        init()
        listeners()
    }

    private fun listeners() {
        editProfileButton.setOnClickListener {
            val link = linkEditText.text.toString()
            val username = usernameEditText.text.toString()
            val userInfo = User(
                auth.currentUser?.email,
                auth.currentUser!!.uid,
                link, username)
            db.child(auth.currentUser!!.uid).setValue(userInfo)

        }

        signOutButton.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            auth.signOut()
            finish()
        }

        profileUpdateButton.setOnClickListener{
            val newPass = updatePasswordTextView.text.toString()
            if(newPass != ""){
                auth.currentUser!!.updatePassword(newPass)
            }else {
                Toast.makeText(this, "Input Password Before Resetting!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init(){
        linkEditText = findViewById(R.id.linkEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        editProfileButton = findViewById(R.id.editProfileButton)
        userNameTextView = findViewById(R.id.userNameTextView)
        imageView = findViewById(R.id.imageView)
        signOutButton = findViewById(R.id.signOutButton)
        updatePasswordTextView = findViewById(R.id.updatePasswordTextView)
        profileUpdateButton = findViewById(R.id.profileUpdateButton)
    }

}