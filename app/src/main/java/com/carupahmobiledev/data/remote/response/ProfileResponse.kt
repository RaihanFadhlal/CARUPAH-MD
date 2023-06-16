package com.carupahmobiledev.data.remote.response

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("numberPhone")
    val numberPhone: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String
)

data class UpdateProfileResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: Any
)

data class UpdatePictureResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: Any
)
