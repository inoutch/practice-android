package io.github.inoutch.practice.android.holder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.inoutch.practice.android.R
import io.github.inoutch.practice.android.model.data.ImageMessage

class ImageMessageRightViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val uploadRightImageView = itemView.findViewById<ImageView>(R.id.uploadRightImageView)
    private val imageTouchable = itemView.findViewById<ImageView>(R.id.imageTouchable)

    fun bind(message: ImageMessage, onClickImage: (imageUrl: String) -> Unit) {
        Glide.with(itemView)
                .load(message.imageUrl)
                .centerCrop()
                .into(uploadRightImageView)

        imageTouchable.setOnClickListener { onClickImage(message.imageUrl) }
    }
}
