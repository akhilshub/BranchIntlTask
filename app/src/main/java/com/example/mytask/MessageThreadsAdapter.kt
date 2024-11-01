package com.yourpackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytask.Message
import com.example.mytask.R

class MessageThreadsAdapter(private val messageThreads: List<Message>) :
    RecyclerView.Adapter<MessageThreadsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageBody: TextView = itemView.findViewById(R.id.message_body)
        val timestamp: TextView = itemView.findViewById(R.id.timestamp)
        val senderId: TextView = itemView.findViewById(R.id.sender_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message_thread, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messageThread = messageThreads[position]
        holder.messageBody.text = messageThread.body
        holder.timestamp.text = messageThread.timestamp
        holder.senderId.text = messageThread.userId.toString()
    }

    override fun getItemCount() = messageThreads.size

    fun getItem(position: Int): Message {
        return messageThreads[position]
    }
}
