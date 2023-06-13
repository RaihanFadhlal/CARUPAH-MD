package com.carupahmobiledev.data.remote.body

import com.google.gson.annotations.SerializedName

data class RegisterBody (
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirm_pw") val confirm_pw: String,
    @SerializedName("numberPhone") val numberPhone : String
)