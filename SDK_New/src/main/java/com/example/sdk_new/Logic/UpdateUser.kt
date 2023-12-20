package com.example.sdk_new.Logic

import com.example.sdk_new.Models.CreateUser
import com.example.sdk_new.Models.UpdateUser
import com.example.sdk_new.Service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.properties.Delegates

class UpdateUser {
    suspend fun Update(user: UpdateUser):Boolean{
        return UserUpdate(user)
    }

    private suspend fun UserUpdate(user: UpdateUser):Boolean{
        return withContext(Dispatchers.IO){
            val retrofitClient = RetrofitClient(2)
            val call : Call<String> = retrofitClient.apiService().UpdateUser(user)
            try{
                val response : Response<String> = call.execute()
                if(response.isSuccessful){
                    response.body()!!.contains("Success")
                }else{
                    false
                }
            }catch (e:Exception) {
                false
            }
        }
    }
}