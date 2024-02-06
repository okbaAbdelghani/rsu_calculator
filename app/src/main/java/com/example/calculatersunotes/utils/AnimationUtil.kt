package com.example.calculatersunotes.utils

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.calculatersunotes.R

class AnimationUtil(val context: Context) {

    fun makeCongratsAnimation(backgroundImage: ImageView, risingPart: LinearLayout ){

        val scaleAnimation = ScaleAnimation(
            6.0f, 1f,  // X-axis scaling from 1.0 to 1.5
            6.0f, 1f,  // Y-axis scaling from 1.0 to 1.5
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point: center of the view
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 1000
        backgroundImage.startAnimation(scaleAnimation)

        val appearFromBottom = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 0f,
            Animation.RELATIVE_TO_PARENT, 1f,
            Animation.RELATIVE_TO_PARENT, 0f,
        )

        appearFromBottom.duration =  1000
        risingPart.startAnimation(appearFromBottom)
    }

}