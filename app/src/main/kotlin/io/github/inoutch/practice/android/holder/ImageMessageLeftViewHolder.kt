package io.github.inoutch.practice.android.holder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.inoutch.practice.android.R
import io.github.inoutch.practice.android.model.data.ImageMessage

class ImageMessageLeftViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val interlocutorImageView = itemView.findViewById<ImageView>(R.id.interlocutorImageView)
    private val uploadLeftImageView = itemView.findViewById<ImageView>(R.id.uploadLeftImageView)

    fun bind(message: ImageMessage, onClickImageView: (imageUrl: String) -> Unit) {
        Glide.with(itemView)
                .load(message.imageUrl)
                .centerCrop()
                .into(uploadLeftImageView)

        Glide.with(itemView)
                .load(message.interlocutorImageUrl)
                .centerCrop()
                .into(interlocutorImageView)

        uploadLeftImageView.setOnClickListener { onClickImageView(message.imageUrl) }
    }
}
