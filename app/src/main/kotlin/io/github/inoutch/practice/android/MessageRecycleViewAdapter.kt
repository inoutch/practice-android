package io.github.inoutch.practice.android

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.inoutch.practice.android.holder.ImageMessageLeftViewHolder
import io.github.inoutch.practice.android.holder.ImageMessageRightViewHolder
import io.github.inoutch.practice.android.holder.TextMessageLeftViewHolder
import io.github.inoutch.practice.android.holder.TextMessageRightViewHolder
import io.github.inoutch.practice.android.model.data.ImageMessage
import io.github.inoutch.practice.android.model.data.Message
import io.github.inoutch.practice.android.model.data.TextMessage

class MessageRecycleViewAdapter(
        context: Context,
        private val linearLayoutManager: LinearLayoutManager,
        private val onClickImage: (imageUrl: String) -> Unit)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var messages = mutableListOf<Message>()

    private val inflater = LayoutInflater.from(context)

    fun append(messages: List<Message>) {
        this.messages.addAll(messages)
        notifyDataSetChanged()
        scrollToNewestPosition()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MessageViewType.forViewType(viewType).createViewHolder(inflater, parent)
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val message = messages.getOrNull(position) ?: return) {
            is TextMessage ->
                when (holder) {
                    is TextMessageLeftViewHolder -> holder.bind(message)
                    is TextMessageRightViewHolder -> holder.bind(message)
                }
            is ImageMessage ->
                when (holder) {
                    is ImageMessageLeftViewHolder -> holder.bind(message, onClickImage)
                    is ImageMessageRightViewHolder -> holder.bind(message, onClickImage)
                }
        }
    }

    fun scrollToNewestPosition() = linearLayoutManager.scrollToPosition(messages.size - 1)

    override fun getItemViewType(position: Int): Int {
        return messages[position].viewType.viewType
    }

    companion object {
        @JvmStatic
        @BindingAdapter("messages")
        fun RecyclerView.bindMessages(messages: List<Message>?) {
            if (messages == null) {
                return
            }

            val adapter = adapter as MessageRecycleViewAdapter
            adapter.append(messages)
        }
    }
}
