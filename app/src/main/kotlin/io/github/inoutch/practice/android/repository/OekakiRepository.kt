package io.github.inoutch.practice.android.repository

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

class OekakiRepository {
    val myPaint = Paint().apply {
        color = Color.rgb(255, 0, 0)
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 20.0f
    }

    private var drafts = mutableListOf<Point>()

    fun startDraft(x: Int, y: Int) {

    }

    fun moveDraft(x: Int, y: Int) {

    }

    fun endDraf(x: Int, y: Int) {

    }
}
