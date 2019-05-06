package io.github.inoutch.practice.android.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.inoutch.practice.android.R
import io.github.inoutch.practice.android.model.data.TextMessage

class TextMessageLeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView = itemView.findViewById<TextView>(R.id.messageLeftContent)
    private val interlocutorImageView = itemView.findViewById<ImageView>(R.id.interlocutorImageView)

    fun bind(message: TextMessage) {
        textView.text = message.content
        Glide.with(itemView)
                .load(message.interlocutorImageUrl)
                .centerCrop()
                .into(interlocutorImageView)
    }
}
