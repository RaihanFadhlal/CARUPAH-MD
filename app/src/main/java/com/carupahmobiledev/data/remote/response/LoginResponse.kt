package com.carupahmobiledev.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("message")
    val message: String ,

    @field:SerializedName("error")
    val error: Boolean
)
