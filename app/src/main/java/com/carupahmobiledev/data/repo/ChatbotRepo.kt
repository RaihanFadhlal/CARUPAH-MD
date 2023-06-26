package com.carupahmobiledev.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.carupahmobiledev.data.remote.body.ChatBody
import com.carupahmobiledev.data.remote.response.ChatbotResponse
import com.carupahmobiledev.data.remote.retrofit.ApiConfig
import com.carupahmobiledev.util.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatbotRepo {

    private val _chatUser = MutableLiveData<ChatbotResponse>()
    val chatUser: LiveData<ChatbotResponse> = _chatUser

    private val _isEnabled = MutableLiveData<Boolean>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _chatMessage = MutableLiveData<Event<String>>()
    val chatMessage: LiveData<Event<String>>
        get() = _chatMessage

    fun getChat(message : String): LiveData<ChatbotResponse> {
        _isEnabled.value = false
        _isLoading.value = true
        val body = ChatBody(message)
        ApiConfig.getApiService3().sendChat(body)
            .enqueue(object : Callback<ChatbotResponse> {
                override fun onResponse(
                    call: Call<ChatbotResponse>,
                    response: Response<ChatbotResponse>,
                ) {
                    _isEnabled.value = true
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _chatUser.postValue(response.body())
                    } else {
                        Log.e(TAG, "onFailure: ${response.message()}")
                        _chatMessage.value = Event("")
                    }
                }

                override fun onFailure(call: Call<ChatbotResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ${t.message}")
                }
            })
        return _chatUser
    }

    companion object {
        private const val TAG = "ChatbotRepository"
    }
}

