package com.example.calculatersunotes.ui.edit.householder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EditHouseholderAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> EditRegion()
            1 -> EditNameEducation()
            2 -> EditSocialStatus()
            3 -> EditOtherInfo()
            else -> throw IllegalArgumentException("Invalid Position: $position")
        }
    }

    override fun getCount(): Int {
        return 4
    }
}