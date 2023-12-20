package com.example.androidapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.androidapp.modelResponse.Survey
import com.example.androidapp.services.RetrofitClient
import com.example.sdk_new.Customization.survey_custom
import com.example.sdk_new.testSDK
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import yuku.ambilwarna.AmbilWarnaDialog

class Get_Surveys : AppCompatActivity() {
    private lateinit var userGuid : TextInputEditText
    private lateinit var AppKey : TextInputEditText
    private lateinit var getSurveyButton : Button
    private var retrofItClient = RetrofitClient(2)
    private var selectedColor: Int = Color.BLACK
    /*private lateinit var CompLogoUrl : TextInputEditText
    private lateinit var CompLogoRad : TextInputEditText
    private lateinit var ProfImgUrl : TextInputEditText
    private lateinit var LanImgUrl : TextInputEditText
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_surveys)
        userGuid = findViewById(R.id.tCsUserGuid)
        AppKey = findViewById(R.id.tCsAppKey)
        getSurveyButton = findViewById(R.id.bCsGetSurveys)
        fun updateTextColor() {
            userGuid.setTextColor(selectedColor)
        }

        fun showColorPickerDialog() {
            val colorPicker = AmbilWarnaDialog(
                this,
                selectedColor,
                object : AmbilWarnaDialog.OnAmbilWarnaListener {
                    override fun onCancel(dialog: AmbilWarnaDialog?) {
                        // Handle cancel event
                    }

                    override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
                        // Handle color selection
                        selectedColor = color
                        updateTextColor()
                    }
                }
            )
            colorPicker.show()
        }


        val gson = Gson()
        getSurveyButton.setOnClickListener(){
            if(userGuid.text?.length != 36){
                userGuid.requestFocus()
                userGuid.error = "Please Enter Valid User Guid"
                return@setOnClickListener
            }
            val intent = testSDK.GetSurveys(this, userGuid.text.toString())
            startActivity(intent)
        }
    }
}