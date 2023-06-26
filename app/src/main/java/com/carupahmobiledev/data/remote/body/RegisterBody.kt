package com.carupahmobiledev.data.remote.body

import com.google.gson.annotations.SerializedName

data class RegisterBody (
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("confirm_pw") val confirm_pw: String,
    @SerializedName("numberPhone") val numberPhone : String
)
data class LoginBody (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class EditBody (
    @SerializedName("name") val name: String,
    @SerializedName("numberPhone") val numberPhone: String,
    @SerializedName("city") val city: String,
    @SerializedName("district") val district: String,
    @SerializedName("subdistrict") val subdistrict: String,
    @SerializedName("address") val address: String
)

data class ChatBody (
    @SerializedName("message") val message: String
)

