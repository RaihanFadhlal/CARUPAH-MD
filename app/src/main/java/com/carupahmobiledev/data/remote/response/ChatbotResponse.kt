package com.carupahmobiledev.data.remote.response

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "chat")
data class ChatbotResponse(
    @PrimaryKey
    @field:SerializedName("message")
    val message: String
)