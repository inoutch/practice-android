package io.github.inoutch.practice.android.model.data

import android.graphics.Point
import java.util.*

data class Line(val points: List<Point>, val uuid: UUID, val weight: Int, val color: Color)
