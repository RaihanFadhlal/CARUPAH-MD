package com.carupahmobiledev.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carupahmobiledev.data.remote.body.LoginBody
import com.carupahmobiledev.data.remote.body.RegisterBody
import com.carupahmobiledev.data.remote.response.LoginResponse
import com.carupahmobiledev.data.remote.response.RegisterResponse
import com.carupahmobiledev.data.remote.retrofit.ApiConfig
import com.carupahmobiledev.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepo {
    private val login = MutableLiveData<LoginResponse>()

    private val _registerUser = MutableLiveData<RegisterResponse>()
    val registerUser: LiveData<RegisterResponse> = _registerUser

    private val _loginUser = MutableLiveData<LoginResponse>()
    val loginUser: LiveData<LoginResponse> = _loginUser

    private val _isEnabled = MutableLiveData<Boolean>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _regMessage = MutableLiveData<Event<String>>()
    val regMessage: LiveData<Event<String>>
        get() = _regMessage

    private val _logMessage = MutableLiveData<Event<String>>()
    val logMessage: LiveData<Event<String>>
        get() = _logMessage

    fun register(name: String, email: String, password: String, confirm_pw : String, numberPhone: String): LiveData<RegisterResponse> {
        _isEnabled.value = false
        _isLoading.value = true
        val body = RegisterBody(name, email, password, confirm_pw, numberPhone)
        ApiConfig.getApiService().register(body)
            .enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>,
                ) {
                    _isEnabled.value = true
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _registerUser.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                        _regMessage.value = Event("")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        return _registerUser
    }

    fun login(email: String, password: String): LiveData<LoginResponse> {
        _isEnabled.value = false
        _isLoading.value = true
        val body = LoginBody(email, password)

        Log.e(TAG, "The Result is $email")

        ApiConfig.getApiService().login(body)
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>,
                ) {
                    _isEnabled.value = true
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _loginUser.postValue(response.body())

                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                        _logMessage.value = Event("")
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        return login
    }

    companion object {
        private const val TAG = "AuthRepository"
    }
}