package com.example.sdk_new.Logic

import com.example.sdk_new.Models.CreateUser
import com.example.sdk_new.Service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class CreateUser{
    suspend fun Create(user: CreateUser):String{
        return  UserCreate(user)
    }

    private suspend  fun UserCreate(user:CreateUser):String{
        return withContext(Dispatchers.IO) {
            val retrofitClient = RetrofitClient(2)
            val call: Call<String> = retrofitClient.apiService().CreateUser(user)
            try {
                val response: Response<String> = call.execute()
                if (response.isSuccessful) {
                    try{
                        val startIndex = response.body() .toString().indexOf("<UserGuid>")
                        val endIndex = response.body() .toString().indexOf("</UserGuid>")
                        response.body()!!.substring(startIndex,endIndex)
                    }catch (e:Exception){
                        "Failed"
                    }
                } else {
                    "API Call failed with code: ${response.code()}"
                }
            } catch (e: Exception) {
                "Error: ${e.message}"
            }
        }
    }
}
