package io.github.inoutch.practice.android

import android.os.Bundle
import androidx.appcompat.app.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

class ChatRoomActivity : AppCompatActivity() {

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)

        supportActionBar?.title = "Sample"

        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContent, ChatRoomFragment())
                .commitAllowingStateLoss()
    }
}
