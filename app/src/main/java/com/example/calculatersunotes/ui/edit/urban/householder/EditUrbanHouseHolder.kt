package com.example.calculatersunotes.ui.edit.urban.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R


class EditUrbanHouseHolder : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var editHouseHolderPagerAdapter: EditUrbanHouseHolderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_urban_house_holder, container, false)

        viewPager = rootView.findViewById(R.id.edit_urban_householder_pager)
        editHouseHolderPagerAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)

        viewPager.adapter = editHouseHolderPagerAdapter

        return rootView
    }

}
