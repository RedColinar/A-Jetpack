package com.opq.a.jetpack.ui.main

import android.content.res.Resources
import androidx.core.content.res.ResourcesCompat

object Candy {
    fun dp2px(dp: Float): Int {
        return (dp * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }
}