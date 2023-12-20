package com.example.sdk_new.Models

import com.google.gson.annotations.SerializedName

data class UpdateUser (
    @SerializedName("UserGuid") val userGuid : String?,
    @SerializedName("RID") val rid : Int,
    @SerializedName("TxId") val txId : String?,
    @SerializedName("ExtMemberId") val extMemberId : String,
    @SerializedName("Country") val country : String,
    @SerializedName("State") val state: String?,
    @SerializedName("FirstName") val firstName :  String?,
    @SerializedName("LastName") val lastName:String?,
    @SerializedName("EmailAddress") val emailAddress : String?,
    @SerializedName("Zip") val zip:String?,
    @SerializedName("Gender") val gender:String,
    @SerializedName("Dob") val dob: String?,
    @SerializedName("Address1") val add1:String?,
    @SerializedName("Address2") val add2:String?,
    @SerializedName("Ethnicity") val ethnicity:Int?,
    @SerializedName("City") val city:String?){

}