package com.example.sdk_new.Service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(baseURL: Int){
    private val Base_Url = if(baseURL == 1 ){
        "https://api4.opinionetwork.com/api/Member/"
    }else{
        "https://stageapi4.opinionetwork.com/api/Member/"
    }
    /*private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .header("AppKey", appKey) // Add your header here
                .build()

            chain.proceed(newRequest)
        }
        .build()
    private fun getAppKeyFromActivity() : String{
        return MainActivity.getAppKey()
    }*/
    private val retrofit: Retrofit =
     Retrofit.Builder()
        .baseUrl(Base_Url)
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun apiService():ApiService{
         return  retrofit.create(ApiService::class.java)
    }
}
