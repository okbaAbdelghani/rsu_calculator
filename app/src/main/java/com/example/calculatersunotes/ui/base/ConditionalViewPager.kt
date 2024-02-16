package com.example.calculatersunotes.ui.base

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class ConditionalViewPager : ViewPager {

    private var isSwipeEnabled = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    fun setSwipeEnabled(enabled: Boolean) {
        isSwipeEnabled = enabled
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return isSwipeEnabled && super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return isSwipeEnabled && super.onTouchEvent(ev)
    }

}