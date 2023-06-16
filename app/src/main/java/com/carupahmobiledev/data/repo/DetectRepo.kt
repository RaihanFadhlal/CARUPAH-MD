package com.carupahmobiledev.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carupahmobiledev.data.remote.response.DetectResponse
import com.carupahmobiledev.data.remote.retrofit.ApiConfig
import com.carupahmobiledev.util.Event
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetectRepo {

    private val _detectImage = MutableLiveData<DetectResponse>()
    val detectImage: LiveData<DetectResponse> = _detectImage

    private val _isEnabled = MutableLiveData<Boolean>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _detMessage = MutableLiveData<Event<String>>()
    val detMessage: LiveData<Event<String>>
        get() = _detMessage


    fun detectImg(image: MultipartBody.Part): LiveData<DetectResponse> {
        _isEnabled.value = false
        _isLoading.value = true
        ApiConfig.getApiService2().detectImg(image)
            .enqueue(object : Callback<DetectResponse> {
                override fun onResponse(
                    call: Call<DetectResponse>,
                    response: Response<DetectResponse>,
                ) {
                    _isEnabled.value = true
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _detectImage.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                        _detMessage.value = Event("")
                    }
                }

                override fun onFailure(call: Call<DetectResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        return _detectImage
    }

    companion object {
        private const val TAG = "DetectRepository"
    }
}