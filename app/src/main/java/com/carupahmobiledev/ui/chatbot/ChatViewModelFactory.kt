package com.carupahmobiledev.ui.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carupahmobiledev.data.repo.ChatbotRepo

class ChatViewModelFactory(
    private val chatRepo: ChatbotRepo
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatbotViewModel::class.java)) {
            return ChatbotViewModel(chatRepo) as T
        }
        throw IllegalArgumentException("error ${modelClass.name}")
    }
}