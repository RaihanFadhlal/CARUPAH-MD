package com.carupahmobiledev.ui.chatbot

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.carupahmobiledev.data.remote.response.ChatbotResponse
import com.carupahmobiledev.data.repo.ChatbotRepo
import com.carupahmobiledev.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatbotViewModel @Inject constructor(
    private val chatRepo: ChatbotRepo,
) : ViewModel() {

    val detMessage: LiveData<Event<String>>
        get() = chatRepo.chatMessage

    val chatUser: LiveData<ChatbotResponse> = chatRepo.chatUser

    val isLoading: LiveData<Boolean> = chatRepo.isLoading

    fun getChat(message : String) = chatRepo.getChat(message)

}