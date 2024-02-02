package com.example.calculatersunotes.ui.edit.urban.householder

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EditUrbanHouseHolderAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
        return when (position)  {
            0 -> EditUrbanNameEducation()
            1 -> EditUrbanSocialStatus()
            2 -> EditUrbanOtherInfo()
            else -> throw IllegalArgumentException("Invalid Position: $position")
        }
    }
    override fun getCount(): Int {
        return 3
    }
}