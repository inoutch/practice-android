package io.github.inoutch.practice.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.inoutch.practice.android.model.MessageViewModel
import io.github.inoutch.practice.android.model.data.TextMessage
import io.github.inoutch.practice.android.view.OekakiDialog
import androidx.fragment.app.viewModels
import io.github.inoutch.practice.android.databinding.FragmentChatRoomBinding

class ChatRoomFragment : Fragment() {
    enum class RequestCode(val code: Int) {
        Picture(1);

        companion object {
            val codes = values().map { it.code to it }.toMap()
        }
    }

    private lateinit var binding: FragmentChatRoomBinding

    private lateinit var adapter: MessageRecycleViewAdapter

    private lateinit var oekakiDialog: OekakiDialog

    private lateinit var editTextMessage: EditText

    private lateinit var buttonSendMessage: Button

    private lateinit var buttonUploadImage: FloatingActionButton

    private val messageViewModel: MessageViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val context = this.context ?: throw AssertionError("context is null")
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_room, container, false)
        binding.lifecycleOwner = this

        editTextMessage = binding.root.findViewById(R.id.editTextMessage)

        buttonSendMessage = binding.root.findViewById(R.id.buttonSendMessage)
        buttonSendMessage.setOnClickListener { this.onClickSendMessage() }

        buttonUploadImage = binding.root.findViewById(R.id.buttonUploadImage)
        buttonUploadImage.setOnClickListener { this.onClickUploadImage() }

        oekakiDialog = OekakiDialog(context)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val context = this.context ?: throw AssertionError("context is null")

        binding.messageViewModel = messageViewModel

        val linearLayoutManager = LinearLayoutManager(context)
        adapter = MessageRecycleViewAdapter(context, linearLayoutManager) { oekakiDialog.show(it) }

        binding.messages.layoutManager = linearLayoutManager
        binding.messages.adapter = adapter

        messageViewModel.initMessages(0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            // ignore
            return
        }

        when (RequestCode.codes[requestCode]) {
            RequestCode.Picture -> messageViewModel.uploadImage(
                    requireContext().contentResolver, data?.data ?: return)
        }
    }

    private fun onClickSendMessage() {
        messageViewModel.sendMessage(editTextMessage.text.toString())
        editTextMessage.setText("", TextView.BufferType.NORMAL)
    }

    private fun onClickUploadImage() {
        // open image selector
        val photoPickerIntent = Intent(Intent.ACTION_PICK)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, 1)
    }
}
