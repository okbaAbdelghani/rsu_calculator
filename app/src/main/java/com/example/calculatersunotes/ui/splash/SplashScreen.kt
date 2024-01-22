package com.example.calculatersunotes.ui.splash

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.MainActivity
import com.example.calculatersunotes.ui.intro.IntroFragment
import com.example.calculatersunotes.ui.onboards.OnboardingActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val bgView: View = findViewById(R.id.kingdom_symbol)

        val fadeIn = ObjectAnimator.ofFloat(bgView, "alpha",0f, 1f)
        fadeIn.duration = 4000

        fadeIn.start()

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },5000)
    }
}