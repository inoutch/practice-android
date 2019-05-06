package io.github.inoutch.practice.android.view

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import io.github.inoutch.practice.android.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class OekakiDialog(context: Context) : Dialog(context) {
    private lateinit var oekakiView: OekakiView

    private lateinit var oekakiDraftView: OekakiDraftView

    fun show(imageUrl: String) {
        setContentView(R.layout.dialog_oekaki)
        window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window?.setBackgroundDrawableResource(android.R.color.transparent)

        oekakiView = findViewById(R.id.oekakiView)
        oekakiDraftView = findViewById(R.id.oekakiDraftView)

        val oekakiBaseImageView = findViewById<ImageView>(R.id.oekakiBaseImageView)

        Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(oekakiBaseImageView)

        super.show()
    }
}
