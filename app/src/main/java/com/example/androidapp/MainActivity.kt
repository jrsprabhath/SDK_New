package com.example.androidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private lateinit var createUser:Button
    private lateinit var updateUser:Button
    private lateinit var deleteUser:Button
    private lateinit var getSurveys:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createUser = findViewById(R.id.bCreateUser)
        updateUser = findViewById(R.id.bUpdateUser)
        deleteUser = findViewById(R.id.bDeleteUser)
        getSurveys = findViewById(R.id.bGetSurveys)

        createUser.setOnClickListener(){
            val intent = Intent(this,Create_User::class.java)
            startActivity(intent)
        }
        updateUser.setOnClickListener(){
            val intent = Intent(this,Update_User::class.java)
            startActivity(intent)
        }
        deleteUser.setOnClickListener(){
            val intent = Intent(this,Delete_User::class.java)
            startActivity(intent)
        }
        getSurveys.setOnClickListener(){
            val intent = Intent(this,Get_Surveys::class.java)
            startActivity(intent)
        }
    }
    companion object {
        // Implement a method to get the app key from your MainActivity
        fun getAppKey(): String {
            return R.string.appKey.toString()
        }
    }
}