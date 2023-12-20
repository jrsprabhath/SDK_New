package com.example.sdk_new

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sdk_new.Logic.GetSurveys

class testSDK():AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    companion object{
        fun GetSurveys(context: Context, userGuid: String): Intent {
            return Intent(context, GetSurveys::class.java).apply {
                putExtra("user_guid", userGuid)
            }
        }
        suspend fun CreateUser( user: com.example.sdk_new.Models.CreateUser):String{
            val createUser = com.example.sdk_new.Logic.CreateUser()
            return createUser.Create(user)
        }

        suspend fun UpdateUser( user: com.example.sdk_new.Models.UpdateUser):Boolean{
            val updateUser = com.example.sdk_new.Logic.UpdateUser()
            return updateUser.Update(user)
        }
        suspend fun DeleteUser(user : com.example.sdk_new.Models.DeleteUser):Boolean{
            val deleteUser = com.example.sdk_new.Logic.DeleteUser()
            return deleteUser.Delete(user)
        }
    }
}