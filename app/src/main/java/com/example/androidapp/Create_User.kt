package com.example.androidapp

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.androidapp.modelResponse.CreateUser
import com.example.androidapp.services.CommonProvider
import com.example.androidapp.services.RetrofitClient
import com.example.sdk_new.testSDK
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class Create_User : AppCompatActivity() {

    private lateinit var RId : TextInputEditText
    private lateinit var TxId : TextInputEditText
    private lateinit var ExtMemId : TextInputEditText
    private lateinit var State : TextInputEditText
    private lateinit var Country : TextInputEditText
    private lateinit var FirstName : TextInputEditText
    private lateinit var LastName : TextInputEditText
    private lateinit var EmailAddress : TextInputEditText
    private lateinit var ZIP : TextInputEditText
    private lateinit var Gender : TextInputEditText
    private lateinit var DOB : TextView
    private lateinit var Add1 : TextInputEditText
    private lateinit var Add2 : TextInputEditText
    private lateinit var Ethnicity : TextInputEditText
    private lateinit var output :String
    private lateinit var City : TextInputEditText
    private lateinit var Submit : Button
    private lateinit var user : com.example.sdk_new.Models.CreateUser
    private val calendar = Calendar.getInstance()
    private val number_pattern = Regex("^\\d+$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
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
        Add1 =  findViewById(R.id.fAdd1)
        Add2 =  findViewById(R.id.fAdd2)
        Ethnicity =  findViewById(R.id.fEthnicity)
        City =  findViewById(R.id.fCity)
        Submit =  findViewById(R.id.bSaveUser)
        val common : CommonProvider = CommonProvider()

        DOB.setOnClickListener(){
            CommonProvider().showDatePicker(this, calendar,DOB)
        }

        fun openDialog(context: Context, layout: Int, text : String){
            val builder = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)
            val dialogView = inflater.inflate(layout,null)
            builder.setView(dialogView)
            val dialog = builder.create()
            val dialogText = dialogView.findViewById<TextView>(R.id.newUserGuid)
            var okButton = dialogView.findViewById<Button>(R.id.dialogButton)
            var copyButton = dialogView.findViewById<ImageButton>(R.id.ugCopyButton)
            dialogText.text = text
            copyButton.setOnClickListener(){
                val clipboardManager = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clipData = ClipData.newPlainText("Copied: ",dialogText.text.toString())
                clipboardManager.setPrimaryClip(clipData)
            }
            dialogText.text = text
            dialog.show()
            okButton.setOnClickListener(){
                dialog.dismiss()
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
        fun makeToast(output:String){
            if(output != "Failed"){
                openDialog(this,R.layout.userguid_dialog,output)
                Toast.makeText(this,"User Created Successfully",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Failed To Create User",Toast.LENGTH_LONG).show()
            }
        }

        Submit.setOnClickListener(){
            if(RId.text.toString().isEmpty()){
                RId.requestFocus()
                RId.error = "Please Enter RId"
                return@setOnClickListener
            }
            if (!number_pattern.matches(RId.text.toString())){
                RId.requestFocus()
                RId.error = "RId should contain only numbers"
                return@setOnClickListener
            }
            if(TxId.text.toString().isEmpty()){
                TxId.requestFocus()
                TxId.error = "Please Enter TxId"
                return@setOnClickListener
            }
            if(!EMAIL_ADDRESS.matcher(EmailAddress.text.toString()).matches()){
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

            user = com.example.sdk_new.Models.CreateUser(
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
                    output = testSDK.CreateUser(user)
                    makeToast(output)
                }catch(e:Exception){
                    println("Error: ${e.message}")
                }
            }
        }
    }
}
