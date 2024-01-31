package com.example.calculatersunotes.ui.edit.householder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EditHouseholderAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> EditNameEducation()
            1 -> EditSocialStatus()
            2 -> EditOtherInfo()
            else -> throw IllegalArgumentException("Invalid Position: $position")
        }
    }

    override fun getCount(): Int {
        return 3
    }
}