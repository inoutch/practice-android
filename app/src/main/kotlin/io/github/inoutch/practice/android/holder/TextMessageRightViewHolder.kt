package io.github.inoutch.practice.android.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.github.inoutch.practice.android.R
import io.github.inoutch.practice.android.model.data.TextMessage

class TextMessageRightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView = itemView.findViewById<TextView>(R.id.messageRightContent)

    fun bind(message: TextMessage) {
        textView.text = message.content
    }
}
