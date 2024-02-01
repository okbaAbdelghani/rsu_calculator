package com.example.calculatersunotes.ui.urban.house

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R

class UrbanHouseFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var urbanHousePagerAdapter: UrbanHousePagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_urban_house, container, false)

        viewPager = rootView.findViewById<ViewPager>(R.id.urban_house_pager)
        urbanHousePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)
        viewPager.adapter = urbanHousePagerAdapter


        return rootView
    }

}