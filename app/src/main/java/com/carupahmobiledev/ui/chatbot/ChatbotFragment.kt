package com.carupahmobiledev.ui.chatbot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.carupahmobiledev.data.repo.ChatbotRepo
import com.carupahmobiledev.databinding.FragmentChatbotBinding
import com.carupahmobiledev.ui.chatbot.Constant.REC_ID
import com.carupahmobiledev.ui.chatbot.Constant.SEND_ID
import kotlinx.coroutines.*

class ChatbotFragment : Fragment() {

    private lateinit var binding: FragmentChatbotBinding
    private lateinit var chatbotVM: ChatbotViewModel
    private lateinit var chatbotRepo: ChatbotRepo
    private lateinit var chatAdapter: ChatbotAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentChatbotBinding.inflate(inflater, container, false)
        chatbotRepo = ChatbotRepo()
        chatbotVM = ViewModelProvider(requireActivity(), ChatViewModelFactory(chatbotRepo))[ChatbotViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView()
        customBotMessage("Hallo! Aku CarupAi, Kamu bisa menanyakan pertanyaan seputar pengelolaan sampahmu!")
        clickEvents()
    }

    private fun recyclerView() {
        chatAdapter = ChatbotAdapter()
        binding.chatbotRv.adapter = chatAdapter
        binding.chatbotRv.layoutManager = LinearLayoutManager(requireActivity())

    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                binding.chatbotRv.scrollToPosition(chatAdapter.itemCount - 1)
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun clickEvents() {
        binding.sendBtn.setOnClickListener {
            sendMessage()
        }
        binding.messageEditText.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    binding.chatbotRv.scrollToPosition(chatAdapter.itemCount - 1)

                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun sendMessage() {
        val message = binding.messageEditText.text.toString()
        if (message.isNotEmpty()) {
            val newMessage = Message(message, SEND_ID)
            chatAdapter.insertMessage(newMessage)
            binding.messageEditText.setText("")
            binding.chatbotRv.scrollToPosition(chatAdapter.itemCount - 1)

            chatbotVM.getChat(message)
            chatbotVM.chatUser.observe(requireActivity()){ response ->
                val answer = response.message
                GlobalScope.launch {
                    delay(1000)
                    withContext(Dispatchers.Main) {
                        val newResponse = Message(answer, REC_ID)
                        chatAdapter.insertMessage(newResponse)
                        binding.chatbotRv.scrollToPosition(chatAdapter.itemCount - 1)
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun customBotMessage(message: String) {
        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val newResponse = Message(message, REC_ID)
                chatAdapter.insertMessage(newResponse)
                binding.chatbotRv.scrollToPosition(chatAdapter.itemCount - 1)
            }
        }
    }
}
