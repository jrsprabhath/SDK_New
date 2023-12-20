package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.sdk_new.Service.CommonProvider
import com.example.sdk_new.testSDK
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.Calendar

class Update_User : AppCompatActivity() {
    private lateinit var RId : TextInputEditText
    private lateinit var TxId : TextInputEditText
    private lateinit var ExtMemId : TextInputEditText
    private lateinit var State : TextInputEditText
    private lateinit var Country : TextInputEditText
    private lateinit var FirstName : TextInputEditText
    private lateinit var LastName : TextInputEditText
    private lateinit var UserGuid : TextInputEditText
    private lateinit var EmailAddress : TextInputEditText
    private lateinit var ZIP : TextInputEditText
    private lateinit var Gender : TextInputEditText
    private lateinit var DOB : TextView
    private lateinit var Add1 : TextInputEditText
    private lateinit var Add2 : TextInputEditText
    private lateinit var Ethnicity : TextInputEditText
    private lateinit var City : TextInputEditText
    private lateinit var Submit : Button
    private var output:Boolean = false
    private lateinit var user : com.example.sdk_new.Models.UpdateUser
    private val calendar = Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)
        RId = findViewById(R.id.fRId)
        TxId = findViewById(R.id.fTxId)
        ExtMemId = findViewById(R.id.fExtMemberId)
        State = findViewById(R.id.fState)
        Country =  findViewById(R.id.fCountry)
        FirstName =  findViewById(R.id.fFirstName)
        LastName =  findViewById(R.id.fLastName)
        EmailAddress =  findViewById(R.id.fEmailAdd)
        ZIP =  findViewById(R.id.fZip)
        Gender =  findViewById(R.id.fGender)
        DOB =  findViewById(R.id.fDob)
        UserGuid = findViewById(R.id.fUg)
        Add1 =  findViewById(R.id.fAdd1)
        Add2 =  findViewById(R.id.fAdd2)
        Ethnicity =  findViewById(R.id.fEthnicity)
        City =  findViewById(R.id.fCity)
        Submit =  findViewById(R.id.bSaveUser)

        DOB.setOnClickListener(){
            CommonProvider().showDatePicker(this, calendar,DOB)
        }

        fun makeToast(deleted:Boolean){
            if(deleted){
                Toast.makeText(this,"User Details Updated Successfully",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Failed To Update User Details",Toast.LENGTH_LONG).show()
            }
        }

        Submit.setOnClickListener(){
            if (UserGuid.text!!.length != 36){
                UserGuid.requestFocus()
                UserGuid.error = "Please Enter Valid User Guid"
                return@setOnClickListener
            }
            if (RId.text.toString().isEmpty()){
                RId.requestFocus()
                RId.error = "Please Enter RId"
                return@setOnClickListener
            }
            if(TxId.text.toString().isEmpty()){
                TxId.requestFocus()
                TxId.error = "Please Enter TxId"
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(EmailAddress.text.toString()).matches()){
                EmailAddress.requestFocus()
                EmailAddress.error="Please Enter Valid Email Address"
                return@setOnClickListener
            }
            if(Country.text.toString().isEmpty()){
                Country.requestFocus()
                Country.error="Please Enter Your Country"
                return@setOnClickListener
            }
            if(State.text.toString().isEmpty()){
                State.requestFocus()
                State.error="Please Enter Your State"
                return@setOnClickListener
            }

            user = com.example.sdk_new.Models.UpdateUser(
                UserGuid.text.toString(),
                RId.text.toString().toInt(),
                TxId.text.toString(),
                ExtMemId.text.toString(),
                Country.text.toString(),
                State.text.toString(),
                FirstName.text.toString(),
                LastName.text.toString(),
                EmailAddress.text.toString(),
                ZIP.text.toString(),
                Gender.text.toString(),
                DOB.text.toString(),
                Add1.text.toString(),
                Add2.text.toString(),
                Ethnicity.text.toString().toInt(),
                City.text.toString()
            )
            lifecycleScope.launch {
                try{
                    output = testSDK.UpdateUser(user)
                    makeToast(output)
                }catch(e:Exception){
                    println("Error: ${e.message}")
                }
            }
        }
    }
}