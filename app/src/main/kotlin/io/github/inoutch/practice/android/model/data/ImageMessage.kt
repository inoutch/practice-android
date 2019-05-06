package io.github.inoutch.practice.android.model.data

import io.github.inoutch.practice.android.MessageViewType

class ImageMessage(
        id: Int,
        interlocutorImageUrl: String?,
        val imageUrl: String)
    : Message(
        id,
        if (interlocutorImageUrl == null) MessageViewType.ImageRight else MessageViewType.ImageLeft,
        interlocutorImageUrl)
