package com.example.calculatersunotes.ui.edit.rural.householder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EditRuralHouseholderAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> EditRuralNameEducation()
            1 -> EditRuralSocialStatus()
            2 -> EditRuralOtherInfo()
            else -> throw IllegalArgumentException("Invalid Position: $position")
        }
    }

    override fun getCount(): Int {
        return 3
    }
}