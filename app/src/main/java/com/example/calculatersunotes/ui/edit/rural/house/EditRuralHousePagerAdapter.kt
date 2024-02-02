package com.example.calculatersunotes.ui.edit.rural.house

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EditRuralHousePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> EditRuralHouseInfoOne()
            1 -> EditRuralHouseInfoTwo()
            2 -> EditRuralHouseInfoThree()
            else -> throw IllegalArgumentException("Invalid Position: $position")
        }
    }

    override fun getCount(): Int {
        return 3
    }
}