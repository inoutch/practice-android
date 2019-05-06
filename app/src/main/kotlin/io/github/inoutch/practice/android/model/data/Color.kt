package io.github.inoutch.practice.android.model.data

import android.graphics.Color

data class Color(val r: Int, val g: Int, val b: Int) {
    constructor(color: Int) : this(Color.red(color), Color.green(color), Color.blue(color))
}
