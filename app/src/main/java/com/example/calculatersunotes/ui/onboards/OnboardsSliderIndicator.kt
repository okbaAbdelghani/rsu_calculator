package com.example.calculatersunotes.ui.onboards

import android.content.Context
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat

class OnboardsSliderIndicator(context: Context, private val dotCount: Int, private val activeDotDrawableResId: Int, private val inactiveDotDrawableResId: Int):LinearLayout(context) {


    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL or Gravity.CENTER_HORIZONTAL
        createDots(dotCount)
    }

    private fun createDots(count: Int){
        for(i in 0 until count){
            val dot = ImageView(context)
            dot.setImageDrawable(ContextCompat.getDrawable(context, inactiveDotDrawableResId))
            val params = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8,0,8,0)
            dot.layoutParams = params
            addView(dot)
        }

        setActiveDot(0)
    }

    fun setActiveDot(position: Int){

        for (i in 0 until childCount) {
            val dot = getChildAt(i) as ImageView
            val drawableResId = if (i == position) activeDotDrawableResId else inactiveDotDrawableResId
            dot.setImageDrawable(ContextCompat.getDrawable(context, drawableResId))
        }
    }
}