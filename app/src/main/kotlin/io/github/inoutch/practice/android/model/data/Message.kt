package io.github.inoutch.practice.android.model.data

import io.github.inoutch.practice.android.MessageViewType

abstract class Message(
        val id: Int,
        val viewType: MessageViewType,
        val interlocutorImageUrl: String?)
