package com.carupahmobiledev.data.remote.retrofit

import com.carupahmobiledev.data.remote.body.RegisterBody
import com.carupahmobiledev.data.remote.response.LoginResponse
import com.carupahmobiledev.data.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    @FormUrlEncoded
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @POST("auth/register")
    fun register(
        @Body body : RegisterBody
    ): Call<RegisterResponse>
}