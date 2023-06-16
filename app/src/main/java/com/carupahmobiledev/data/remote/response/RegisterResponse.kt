package com.carupahmobiledev.data.remote.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("message")
    val message: String,
)
data class Data(

    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("email")
    val email: String
)

