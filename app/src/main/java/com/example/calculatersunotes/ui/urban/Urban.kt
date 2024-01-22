package com.example.calculatersunotes.ui.urban

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R


class Urban : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var urbanPagerAdapter: UrbanPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_urban, container, false)

        viewPager = rootView.findViewById<ViewPager>(R.id.questions_container)
        urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
        viewPager.adapter = urbanPagerAdapter

        return rootView
    }

}