package io.github.inoutch.practice.android.repository

import android.graphics.Color
import android.graphics.Paint
import io.github.inoutch.practice.android.model.data.Line
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel

@ExperimentalCoroutinesApi
class OekakiRepository {
    val myPaint = Paint().apply {
        color = Color.rgb(255, 0, 0)
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 20.0f
    }

    val linesBroadcastChannel = BroadcastChannel<Line>(3)

    fun postLine(line: Line) {
        GlobalScope.launch {
            delay(3000)
            linesBroadcastChannel.send(line)
        }
    }
}
