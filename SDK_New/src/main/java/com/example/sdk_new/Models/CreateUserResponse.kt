package com.example.sdk_new.Models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    @SerialName("message")
    val message: Int,
    @SerialName("name")
    val UserGuid: String
)