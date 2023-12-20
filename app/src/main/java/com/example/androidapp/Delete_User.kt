package com.example.androidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.sdk_new.Models.DeleteUser
import com.example.sdk_new.testSDK
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class Delete_User : AppCompatActivity() {
    private lateinit var rId : TextInputEditText
    private var output : Boolean = false
    private lateinit var extMemId : TextInputEditText
    private lateinit var userGuid : TextInputEditText
    private lateinit var user : DeleteUser
    private lateinit var deleteButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_user)
        rId = findViewById(R.id.fdRid)
        extMemId = findViewById(R.id.fdExtMemId)
        userGuid = findViewById(R.id.fdUserGuid)
        deleteButton = findViewById(R.id.bDeleteUser)

        fun makeToast(deleted:Boolean){
            if(deleted){
                Toast.makeText(this,"User Deleted Successfully",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Failed to Delete User",Toast.LENGTH_LONG).show()
            }
        }

        deleteButton.setOnClickListener(){

            if (rId.text.toString().isEmpty()){
                rId.requestFocus()
                rId.error = "Please Enter RId"
                return@setOnClickListener
            }
            if(extMemId.text.toString().isEmpty()){
                extMemId.requestFocus()
                extMemId.error = "Please Enter Ext Member Id"
                return@setOnClickListener
            }
            if(userGuid.text.toString().isEmpty()){
                userGuid.requestFocus()
                userGuid.error="Please Enter User Guid"
                return@setOnClickListener
            }
            if(userGuid.text!!.length != 36 ){
                userGuid.requestFocus()
                userGuid.error="Please Enter Valid User Guid"
                return@setOnClickListener
            }
            user = DeleteUser(
                rId.text.toString().toInt(),
                extMemId.text.toString(),
                userGuid.text.toString()
            )
            lifecycleScope.launch {
                try{
                    output = testSDK.DeleteUser(user)
                    makeToast(output)
                }catch(e:Exception){
                    println("Error: ${e.message}")
                }
            }
        }
    }
}