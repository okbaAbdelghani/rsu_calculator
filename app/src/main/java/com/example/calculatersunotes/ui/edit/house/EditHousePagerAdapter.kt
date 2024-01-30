package com.example.calculatersunotes.ui.edit.house

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class EditHousePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            1 -> throw IllegalArgumentException("Invalid Position: $position")
            2 -> throw IllegalArgumentException("Invalid Position: $position")
            else -> throw IllegalArgumentException("Invalid Position: $position")
        }
    }

    override fun getCount(): Int {
        return 1
    }
}