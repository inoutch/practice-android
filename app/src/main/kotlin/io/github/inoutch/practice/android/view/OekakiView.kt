package io.github.inoutch.practice.android.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import io.github.inoutch.practice.android.model.data.ManagedPath
import io.github.inoutch.practice.android.repository.OekakiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class OekakiView(context: Context, attributeSet: AttributeSet? = null)
    : View(context, attributeSet), KoinComponent {

    private val oekakiRepository by inject<OekakiRepository>()

    private val path = ManagedPath()

    private val job = GlobalScope.launch {
        oekakiRepository.linesBroadcastChannel.consumeEach { line ->
            path.detach(line.weight, line.color)
            line.points.forEach { path.add(it.x.toFloat(), it.y.toFloat()) }
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path.path, oekakiRepository.myPaint)
    }

    override fun onDetachedFromWindow() {
        job.cancel()
        super.onDetachedFromWindow()
    }
}
