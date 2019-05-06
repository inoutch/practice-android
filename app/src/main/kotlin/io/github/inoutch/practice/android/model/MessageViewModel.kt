package io.github.inoutch.practice.android.model

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.inoutch.practice.android.model.data.ImageMessage
import io.github.inoutch.practice.android.model.data.Message
import io.github.inoutch.practice.android.model.data.TextMessage
import io.github.inoutch.practice.android.repository.MessageRepository
import io.github.inoutch.practice.android.util.NetUtil
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.io.FileNotFoundException
import kotlin.random.Random

class MessageViewModel : ViewModel(), KoinComponent {
    val messages = MutableLiveData<List<Message>>()

    private val messageRepository by inject<MessageRepository>()

    fun initMessages(chatRoomId: Int) {
        messages.postValue(messageRepository.findByChatRoomId(chatRoomId))
    }

    fun uploadImage(contentResolver: ContentResolver, uri: Uri) {
        val filePath = NetUtil.convertToFile(contentResolver, uri)?.absolutePath ?: return
        val fileExt = filePath.substring(filePath.lastIndexOf(".") + 1)

        try {
            if (fileExt == "img" || fileExt == "jpg" || fileExt == "jpeg" || fileExt == "gif" || fileExt == "png") {
                //FINE
                messages.postValue(listOf(ImageMessage(1, null,
                        "https://picsum.photos/350/250/?random&a=${Random.nextInt()}")))
            } else {
                //NOT IN REQUIRED FORMAT
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }

    fun sendMessage(content: String) {
        messages.postValue(listOf(TextMessage(1, null, content)))
    }
}
