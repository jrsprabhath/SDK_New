package com.example.androidapp.modelResponse

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    @SerialName("message")
    val message: Int,
    @SerialName("name")
    val UserGuid: String
)