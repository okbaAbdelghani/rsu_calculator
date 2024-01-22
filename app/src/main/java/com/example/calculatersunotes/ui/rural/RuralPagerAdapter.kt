package com.example.calculatersunotes.ui.rural

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.calculatersunotes.ui.rural.householder.OtherRuralPatriarchInfoFragment
import com.example.calculatersunotes.ui.rural.householder.RuralNameEducation
import com.example.calculatersunotes.ui.rural.householder.RuralSocialStatus

class RuralPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> RuralNameEducation()
            1 -> RuralSocialStatus()
            2 -> OtherRuralPatriarchInfoFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getCount(): Int {
        return 3
    }

}