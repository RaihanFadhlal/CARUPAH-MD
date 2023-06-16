package com.carupahmobiledev.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carupahmobiledev.data.remote.body.EditBody
import com.carupahmobiledev.data.remote.response.ProfileResponse
import com.carupahmobiledev.data.remote.response.UpdateProfileResponse
import com.carupahmobiledev.data.remote.retrofit.ApiConfig
import com.carupahmobiledev.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileRepo {

    private val _profileUser = MutableLiveData<ProfileResponse>()
    val profileUser: LiveData<ProfileResponse> = _profileUser

    private val _updateUser = MutableLiveData<UpdateProfileResponse>()
    val updateUser: LiveData<UpdateProfileResponse> = _updateUser

    private val _isEnabled = MutableLiveData<Boolean>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _profileMessage = MutableLiveData<Event<String>>()
    val profileMessage: LiveData<Event<String>>
        get() = _profileMessage

    fun getProfile(id: String): LiveData<ProfileResponse> {
        _isEnabled.value = false
        _isLoading.value = true

        ApiConfig.getApiService().getProfile(id)
            .enqueue(object : Callback<ProfileResponse> {
                override fun onResponse(
                    call: Call<ProfileResponse>,
                    response: Response<ProfileResponse>,
                ) {
                    _isEnabled.value = true
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _profileUser.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                        _profileMessage.value = Event("")
                    }
                }

                override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        return _profileUser
    }

    fun editProfile(id: String, bodyEdit: EditBody) {
        _isEnabled.value = false
        _isLoading.value = true

        ApiConfig.getApiService().editProfile(id, bodyEdit)
            .enqueue(object : Callback<UpdateProfileResponse> {
                override fun onResponse(
                    call: Call<UpdateProfileResponse>,
                    response: Response<UpdateProfileResponse>,
                ) {
                    _isEnabled.value = true
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _updateUser.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                        _profileMessage.value = Event("")
                    }
                }

                override fun onFailure(call: Call<UpdateProfileResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
    }

    companion object {
        private const val TAG = "ProfileRepository"
    }
}