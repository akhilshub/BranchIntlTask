package com.example.mytask

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class ConversationActivity : AppCompatActivity() {
    private lateinit var conversationRecyclerView: RecyclerView
    private lateinit var messageInputEditText: EditText
    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)

        conversationRecyclerView = findViewById(R.id.conversation)
        messageInputEditText = findViewById(R.id.message_input)
        sendButton = findViewById(R.id.send_button)

        sendButton.setOnClickListener {
            val messageBody = messageInputEditText.text.toString()
            sendMessage(messageBody)
        }
    }

    private fun sendMessage(messageBody: String) {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        val messageRequest = MessageRequest(threadId = 1, body = messageBody)

        lifecycleScope.launch {
            val authToken = "your_auth_token"  // Replace with actual authToken
            val response = apiService.sendMessage(authToken, messageRequest)
            if (response.isSuccessful) {
                //Toast.message("Message sent")
            } else {
                // Handle error
            }
        }
    }
}
