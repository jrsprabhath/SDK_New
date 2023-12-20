package com.example.androidapp.modelResponse

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class Survey(
    @SerializedName("OrgId") val OrgId: Int,
    @SerializedName("PriorityId")val PriorityId: Int,
    @SerializedName("ProjectId")val ProjectId: Int,
    @SerializedName("TargetGuid")val TargetGuid: String,
    @SerializedName("SurveyName")val SurveyName: String,
    @SerializedName("SurveyLength")val SurveyLength: Int,
    @SerializedName("PartnerRevenueShare")val PartnerRevenueShare : Float,
    @SerializedName("MemberReward")val MemberReward : Float,
    @SerializedName("MemberRewardPoints")val MemberRewardPoints : Float,
    @SerializedName("SurveyUserTypeIds")val SurveyUserTypeIds: String,
    @SerializedName("SurveyUrl")val SurveyUrl : String,
    @SerializedName("Ir")val Ir : Int,
    @SerializedName("Message")val Message : String,
    @SerializedName("RewardText")val RewardText : String,
    @SerializedName("BuyerID")val BuyerID : Int){
}