package com.example.calculatersunotes.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.calculatersunotes.R

class AnimationUtil(val context: Context) {

    fun makeCongratsAnimation(backgroundImage: ImageView, risingPart: LinearLayout){

        val backgroundAnimation = AnimationUtils.loadAnimation(context, R.anim.background_scale_animation)
        backgroundImage.startAnimation(backgroundAnimation)

        val appearFromBottom = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 1f,
            Animation.RELATIVE_TO_PARENT, 0f,
        )

        appearFromBottom.duration =  4000
        risingPart.startAnimation(appearFromBottom)
    }
}