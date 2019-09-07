package com.opq.a.jetpack.ui.main

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.opq.a.jetpack.R

class PageLayoutBehavior(context: Context, attr: AttributeSet) :
    CoordinatorLayout.Behavior<PageLayout>(context, attr) {

    private lateinit var layTitle: TitleLayout

    private var simpleTopDistance = 0

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: PageLayout,
        dependency: View
    ): Boolean {
        when {
            dependency.id == R.id.vTitleLayout -> layTitle = dependency as TitleLayout
            else -> return false
        }
        return true
    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: PageLayout,
        layoutDirection: Int
    ): Boolean {

        val lp = child.layoutParams as CoordinatorLayout.LayoutParams
        if (lp.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            simpleTopDistance = lp.topMargin - layTitle.height
            lp.height = parent.height - layTitle.height
            child.layoutParams = lp
            return true
        }

        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: PageLayout,
        dependency: View
    ): Boolean {
        return true
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: PageLayout,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return true
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: PageLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        val ableToScrollUp = child.translationY == 0f && dy > 0
        val ableToScrollDown = child.translationY < 0
        if (!child.canNestedScrollVertically() && (ableToScrollDown || ableToScrollUp)) {
            val effect = layTitle.effectByOffset(dy)
            val transY = -simpleTopDistance * effect
            child.translationY = transY

            if (effect != 1f) {
                consumed[1] = dy
            }
        }
    }
}