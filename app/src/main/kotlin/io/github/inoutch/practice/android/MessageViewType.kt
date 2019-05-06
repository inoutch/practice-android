package io.github.inoutch.practice.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.inoutch.practice.android.holder.ImageMessageLeftViewHolder
import io.github.inoutch.practice.android.holder.ImageMessageRightViewHolder
import io.github.inoutch.practice.android.holder.TextMessageLeftViewHolder
import io.github.inoutch.practice.android.holder.TextMessageRightViewHolder

enum class MessageViewType(val viewType: Int) {
    TextLeft(0) {
        override fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): RecyclerView.ViewHolder {
            return TextMessageLeftViewHolder(inflater.inflate(R.layout.text_message_left, viewGroup, false))
        }
    },
    TextRight(1) {
        override fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): RecyclerView.ViewHolder {
            return TextMessageRightViewHolder(inflater.inflate(R.layout.text_message_right, viewGroup, false))
        }
    },
    ImageLeft(2) {
        override fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): RecyclerView.ViewHolder {
            return ImageMessageLeftViewHolder(inflater.inflate(R.layout.image_message_left, viewGroup, false))
        }
    },
    ImageRight(3) {
        override fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): RecyclerView.ViewHolder {
            return ImageMessageRightViewHolder(inflater.inflate(R.layout.image_message_right, viewGroup, false))
        }
    };

    companion object {
        fun forViewType(id: Int) = values().find { it.viewType == id }
                ?: throw AssertionError("invalid view type")
    }

    abstract fun createViewHolder(inflater: LayoutInflater, viewGroup: ViewGroup?): RecyclerView.ViewHolder
}
