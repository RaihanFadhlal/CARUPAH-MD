package com.carupahmobiledev.data.remote.retrofit

import com.carupahmobiledev.data.remote.body.LoginBody
import com.carupahmobiledev.data.remote.body.RegisterBody
import com.carupahmobiledev.data.remote.response.LoginResponse
import com.carupahmobiledev.data.remote.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("auth/login")
    fun login(
        @Body bodyLogin : LoginBody
    ): Call<LoginResponse>

    @POST("auth/register")
    fun register(
        @Body bodyRegister : RegisterBody
    ): Call<RegisterResponse>
}