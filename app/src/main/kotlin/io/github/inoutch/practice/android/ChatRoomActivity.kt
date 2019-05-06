package io.github.inoutch.practice.android

import android.os.Bundle
import androidx.appcompat.app.*

class ChatRoomActivity : AppCompatActivity() {

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
