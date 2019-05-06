package io.github.inoutch.practice.android.model.data

import android.graphics.Path
import android.graphics.Point
import java.util.*

class ManagedPath {

    val path = Path()

    private var size = 0

    private val lines = mutableListOf<Line>()

    private var currentLine = Line(mutableListOf(), UUID.randomUUID(), 0, Color(0))

    fun add(x: Float, y: Float) = synchronized(lines) {
        if (size++ == 0) {
            path.moveTo(x, y)
            lines.add(currentLine)
        } else {
            path.lineTo(x, y)
        }
        currentLine.points.add(Point(x.toInt(), y.toInt()))
        currentLine
    }

    fun detach(weight: Int, color: Color) {
        size = 0
        currentLine = Line(mutableListOf(), UUID.randomUUID(), weight, color)
    }

    fun remove(uuid: UUID) = synchronized(lines) {
        lines.removeIf { it.uuid == uuid }
        path.reset()
        lines.forEach { l ->
            size = 0
            l.points.forEach {
                if (size++ == 0) {
                    path.moveTo(it.x.toFloat(), it.y.toFloat())
                } else {
                    path.lineTo(it.x.toFloat(), it.y.toFloat())
                }
            }
        }
    }
}
