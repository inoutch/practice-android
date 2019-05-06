package io.github.inoutch.practice.android.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import io.github.inoutch.practice.android.repository.OekakiRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class OekakiDraftView(context: Context, attributeSet: AttributeSet? = null)
    : View(context, attributeSet), KoinComponent {

    private val path = Path()

    private val oekakiRepository by inject<OekakiRepository>()

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, oekakiRepository.myPaint)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate()
            }
            MotionEvent.ACTION_UP -> {
                path.lineTo(x, y)
                invalidate()
            }
        }
        return true
    }
}
