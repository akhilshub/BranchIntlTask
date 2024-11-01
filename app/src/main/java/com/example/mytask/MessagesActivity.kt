package com.yourpackage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.example.mytask.ConversationActivity
import com.example.mytask.R
import com.example.mytask.RecyclerItemClickListener
import com.example.mytask.RetrofitClient
import kotlinx.coroutines.launch

class MessagesActivity : AppCompatActivity() {
    private lateinit var messageThreadsRecyclerView: RecyclerView
    private lateinit var messageThreadsAdapter: MessageThreadsAdapter
    private var authToken: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messages)

        authToken = intent.getStringExtra("auth_token")

        messageThreadsRecyclerView = findViewById(R.id.message_threads)
        messageThreadsRecyclerView.layoutManager = LinearLayoutManager(this)

        loadMessageThreads()

        messageThreadsRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(this, messageThreadsRecyclerView, object : RecyclerItemClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val messageThread = messageThreadsAdapter.getItem(position)
                    val intent = Intent(this@MessagesActivity, ConversationActivity::class.java)
                    intent.putExtra("thread_id", messageThread.threadId)
                    intent.putExtra("auth_token", authToken)
                    startActivity(intent)
                }

                override fun onLongItemClick(view: View, position: Int) {
                    // Do nothing
                }
            })
        )
    }

    private fun loadMessageThreads() {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)

        lifecycleScope.launch {
            val response = apiService.getMessages(authToken ?: "")
            if (response.isSuccessful) {
                val messageThreads = response.body()
                messageThreadsAdapter = MessageThreadsAdapter(messageThreads)
                messageThreadsRecyclerView.adapter = messageThreadsAdapter
            } else {
                // Handle error
            }
        }
    }
}
