package com.carupahmobiledev.data.remote.retrofit

import com.carupahmobiledev.data.remote.body.EditBody
import com.carupahmobiledev.data.remote.body.LoginBody
import com.carupahmobiledev.data.remote.body.RegisterBody
import com.carupahmobiledev.data.remote.response.*
import okhttp3.MultipartBody
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

    @GET("profile/{{id}}")
    fun getProfile(
        @Path("id") id : String,
    ): Call<ProfileResponse>

    @PATCH("profile/{{id}}")
    fun editProfile(
        @Path("id") id : String,
        @Body bodyEdit : EditBody
    ): Call<UpdateProfileResponse>

    @PATCH("profile/profile-picture/{id}")
    @Multipart
    fun updateProfilePicture(
        @Path("id") id: String,
        @Part image: MultipartBody.Part
    ): Call<UpdatePictureResponse>
    @Multipart
    @POST("predict")
    fun detectImg(
        @Part image: MultipartBody.Part
    ): Call<DetectResponse>

}