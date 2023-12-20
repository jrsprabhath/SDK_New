package com.example.sdk_new.Service

import com.example.sdk_new.Models.CreateUser
import com.example.sdk_new.Models.Survey
import com.example.sdk_new.Models.UpdateUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService{

    @Headers("Content-Type: application/json","AppKey: ADD97C38-474B-493F-9B03-777F7BFE634A")
    @POST("Create") // Replace with your actual endpoint
    fun CreateUser(@Body requestBody: CreateUser
    ):Call<String>

    @Headers("Content-Type: application/json","AppKey: ADD97C38-474B-493F-9B03-777F7BFE634A")
    @POST("Update") // Replace with your actual endpoint
    fun UpdateUser(@Body requestBody: UpdateUser
    ):Call<String>

    @Headers("AppKey: ADD97C38-474B-493F-9B03-777F7BFE634A")
    @POST("Delete")
    fun DeleteUser(@Query("Rid") rId: Int, @Query("ExtMemberId") extMemId: String,@Query("UserGuid") userGuid: String
    ):Call<String>

    @Headers("AppKey: ADD97C38-474B-493F-9B03-777F7BFE634A")
    @POST("GetSurveys")
    fun GetSurveys(@Query("UserGuid") userGuid: String
    ):Call<List<Survey>>
}