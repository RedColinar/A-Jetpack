package com.opq.a.jetpack.ui.main

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.FrameLayout
import com.opq.a.jetpack.R
import com.opq.a.jetpack.ui.Candy.dp2px

class TitleLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val adInterpolator = AccelerateDecelerateInterpolator()
    private val argbEvaluator = ArgbEvaluator()
    private val offsetMax = dp2px(220f).toFloat()
    private var offset = 0f

    init {
        LayoutInflater.from(context).inflate(R.layout.title_layout, this)
    }

    fun effectByOffset(dy: Int): Float {
        if (dy > 0 && offset == offsetMax) return 1f
        else if (dy < 0 && offset == 0f) return 0f

        offset += dy
        if (offset > offsetMax) offset = offsetMax
        else if (offset < 0) offset = 0f

        val fraction = adInterpolator.getInterpolation(offset / offsetMax)

        setBackgroundColor(argbEvaluator.evaluate(fraction, Color.TRANSPARENT, 0xFFFAFAFA.toInt()) as Int)

        return fraction
    }
}