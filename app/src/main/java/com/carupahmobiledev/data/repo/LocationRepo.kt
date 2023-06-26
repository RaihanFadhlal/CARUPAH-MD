package com.carupahmobiledev.data.repo

import com.carupahmobiledev.data.remote.response.BankSampahDetail
import com.carupahmobiledev.data.remote.retrofit.ApiService
import com.carupahmobiledev.ui.location.ApiCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationRepo {

    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.128.86.162:8080/") // Ganti dengan URL basis API Anda
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun getBankSampahList(callback: ApiCallback<List<BankSampahDetail>>) {
        val call = apiService.getBankSampahlist()
        call.enqueue(object : Callback<List<BankSampahDetail>> {
            override fun onResponse(
                call: Call<List<BankSampahDetail>>,
                response: Response<List<BankSampahDetail>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let { callback.onSuccess(it) }
                } else {
                    callback.onError("Request failed with code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<BankSampahDetail>>, t: Throwable) {
                callback.onError(t.message ?: "Request failed.")
            }
        })
    }

}