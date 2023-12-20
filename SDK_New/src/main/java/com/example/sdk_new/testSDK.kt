package com.example.sdk_new

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sdk_new.Adapter.Adapter
import com.example.sdk_new.Logic.DeleteUser
import com.example.sdk_new.Logic.GetSurveys
import com.example.sdk_new.Logic.UpdateUser
import com.example.sdk_new.Models.CreateUser
import com.example.sdk_new.Models.Survey
import com.example.sdk_new.Service.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.awaitAll
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class testSDK():AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    companion object{
        fun GetSurveys(context: Context, userGuid: String): Intent {
            return Intent(context, GetSurveys::class.java).apply {
                putExtra(GetSurveys.USER_GUID_EXTRA, userGuid)
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