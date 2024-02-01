package com.example.calculatersunotes.ui.urban.house

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class UrbanHousePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> UrbanHouseServeyOne()
            1 -> UrbanHouseServeyTwo()
            2 -> UrbanHouseServeyThree()
            3 -> UrbanHouseServeyFour()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getCount(): Int {
        return 4
    }
}