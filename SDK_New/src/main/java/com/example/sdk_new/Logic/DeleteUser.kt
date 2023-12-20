package com.example.sdk_new.Logic

import com.example.sdk_new.Models.DeleteUser
import com.example.sdk_new.Models.UpdateUser
import com.example.sdk_new.Service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class DeleteUser {
    suspend fun Delete(user:DeleteUser):Boolean{
        return UserDelete(user)
    }

    private suspend fun UserDelete(user: DeleteUser):Boolean{
        return withContext(Dispatchers.IO) {
            val retrofitClient = RetrofitClient(2)
            val call : Call<String> = retrofitClient.apiService().DeleteUser(user.rId,user.extMemberId,user.userGuid)
            try{
                val response : Response<String> = call.execute()
                if(response.isSuccessful){
                    response.body()!!.contains("Deleted")
                }else{
                    false
                }
            }catch (e:Exception){
                false
            }
        }
    }
}