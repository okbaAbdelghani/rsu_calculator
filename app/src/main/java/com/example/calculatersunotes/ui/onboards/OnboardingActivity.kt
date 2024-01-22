package com.example.calculatersunotes.ui.onboards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager
    private lateinit var onboardingAdapter: OnboardingPagerAdapter
    private lateinit var indicatorLyout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        viewPager = findViewById(R.id.viewPager)
        onboardingAdapter = OnboardingPagerAdapter(supportFragmentManager)
        viewPager.adapter = onboardingAdapter

        val activeDotDrawableResId = resources.getIdentifier("activeindicator","drawable", "com.example.calculatersunotes")
        val inactiveDotDrawableResId = resources.getIdentifier("inactive_dot","drawable", "com.example.calculatersunotes")

        indicatorLyout = findViewById(R.id.mainLayout)
        val onboardsSliderIndicator = OnboardsSliderIndicator(this, 3, activeDotDrawableResId , inactiveDotDrawableResId )
        indicatorLyout.addView(onboardsSliderIndicator)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                onboardsSliderIndicator.setActiveDot(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }
}