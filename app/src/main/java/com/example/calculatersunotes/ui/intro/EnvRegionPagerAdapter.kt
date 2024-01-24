package com.example.calculatersunotes.ui.intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.calculatersunotes.ui.intro.environments.EnvironmentsFragment
import com.example.calculatersunotes.ui.intro.regions.RegionsFragment

class EnvRegionPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> EnvironmentsFragment()
            1 -> RegionsFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }



    }

    override fun getCount(): Int {
        return 2
    }
}