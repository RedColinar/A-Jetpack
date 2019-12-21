package com.opq.a.jetpack.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.opq.a.jetpack.R
import kotlinx.android.synthetic.main.page_layout.view.*

class PageLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.page_layout, this)
    }

    fun canNestedScrollVertically(): Boolean {
        return nested_scroll_view.canScrollVertically(-1)
    }
}