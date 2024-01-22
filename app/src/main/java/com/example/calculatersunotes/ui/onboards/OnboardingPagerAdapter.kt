package com.example.calculatersunotes.ui.onboards

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.calculatersunotes.ui.onboards.OnboardingScreen1Fragment
import com.example.calculatersunotes.ui.onboards.OnboardingScreen2Fragment
import com.example.calculatersunotes.ui.onboards.OnboardingScreen3Fragment

class OnboardingPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> OnboardingScreen1Fragment()
            1 -> OnboardingScreen2Fragment()
            2 -> OnboardingScreen3Fragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 3
    }
}