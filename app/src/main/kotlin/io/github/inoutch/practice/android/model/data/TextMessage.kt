package io.github.inoutch.practice.android.model.data

import io.github.inoutch.practice.android.MessageViewType

class TextMessage(
        id: Int,
        interlocutorImageUrl: String?,
        val content: String)
    : Message(
        id,
        if (interlocutorImageUrl == null) MessageViewType.TextRight else MessageViewType.TextLeft,
        interlocutorImageUrl)
