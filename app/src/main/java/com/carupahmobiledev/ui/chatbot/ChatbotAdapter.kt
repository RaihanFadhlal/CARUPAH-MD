package com.carupahmobiledev.ui.chatbot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carupahmobiledev.databinding.ItemChatBinding
import com.carupahmobiledev.ui.chatbot.Constant.REC_ID
import com.carupahmobiledev.ui.chatbot.Constant.SEND_ID

class ChatbotAdapter : RecyclerView.Adapter<ChatbotAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    @Suppress("DEPRECATION")
    inner class MessageViewHolder(internal val binding: ItemChatBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ItemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.binding.tvMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvBotMessage.visibility = View.GONE
            }
            REC_ID -> {
                holder.binding.tvBotMessage.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.binding.tvMessage.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message) {
        if (!messagesList.contains(message)) {
            messagesList.add(message)
            notifyItemInserted(messagesList.size - 1)
        }
    }

    fun clearMessages() {
        messagesList.clear()
        notifyDataSetChanged()
    }
}

