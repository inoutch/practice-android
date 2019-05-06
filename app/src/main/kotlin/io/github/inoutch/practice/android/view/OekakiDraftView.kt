package io.github.inoutch.practice.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import io.github.inoutch.practice.android.model.data.Color
import io.github.inoutch.practice.android.model.data.ManagedPath
import io.github.inoutch.practice.android.repository.OekakiRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.consumeEach
import org.koin.core.KoinComponent
import org.koin.core.inject

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
class OekakiDraftView(context: Context, attributeSet: AttributeSet? = null)
    : View(context, attributeSet), KoinComponent {

    private val oekakiRepository by inject<OekakiRepository>()

    private var path = ManagedPath()

    private val job = GlobalScope.launch {
        oekakiRepository.linesBroadcastChannel.consumeEach {
            path.remove(it.uuid)
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path.path, oekakiRepository.myPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.detach(oekakiRepository.myPaint.strokeWidth.toInt(), Color(oekakiRepository.myPaint.color))
                path.add(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                path.add(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                val line = path.add(x, y)
                invalidate()
                oekakiRepository.postLine(line)
            }
        }
        return true
    }

    override fun onDetachedFromWindow() {
        job.cancel()
        super.onDetachedFromWindow()
    }
}
