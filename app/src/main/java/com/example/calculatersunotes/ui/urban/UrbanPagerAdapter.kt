package com.example.calculatersunotes.ui.urban

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.calculatersunotes.ui.urban.householder.OtherUrbanPatriarchInfoFragment
import com.example.calculatersunotes.ui.urban.householder.UrbanNameEducation
import com.example.calculatersunotes.ui.urban.householder.UrbanSocialStatus

class UrbanPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> UrbanNameEducation()
            1 -> UrbanSocialStatus()
            2 -> OtherUrbanPatriarchInfoFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getCount(): Int {
        return 3
    }

}