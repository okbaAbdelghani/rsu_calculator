package com.example.calculatersunotes.ui.rural.house

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.rural.RuralPagerAdapter


class RuralHouseFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var ruralHousePagerAdapter: HousePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_rural_house, container, false)
        viewPager = rootView.findViewById<ViewPager>(R.id.rural_house_pager)
        ruralHousePagerAdapter = HousePagerAdapter(childFragmentManager)
        viewPager.adapter = ruralHousePagerAdapter

        return rootView
    }
}