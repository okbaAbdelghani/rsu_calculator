package com.example.calculatersunotes.ui.urban

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.calculatersunotes.ui.urban.patriarch.UrbanNameEducation
import com.example.calculatersunotes.ui.urban.patriarch.UrbanSocialStatus

class UrbanPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UrbanNameEducation()
            1 -> UrbanSocialStatus()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getCount(): Int {
        return 2
    }

}