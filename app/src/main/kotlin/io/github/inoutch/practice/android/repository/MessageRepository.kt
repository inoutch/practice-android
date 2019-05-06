package io.github.inoutch.practice.android.repository

import io.github.inoutch.practice.android.model.data.Message
import io.github.inoutch.practice.android.model.data.TextMessage

class MessageRepository {
    fun findByChatRoomId(id: Int): List<Message> {
        return List(100) {
            listOf(
                    TextMessage(1, null, "こんにちは$it"),
                    TextMessage(1, "https://picsum.photos/id/615/350/250", "さようなら$it"))
        }.flatten().shuffled()
    }
}
