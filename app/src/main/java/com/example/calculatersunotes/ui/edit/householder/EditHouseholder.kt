package com.example.calculatersunotes.ui.edit.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.edit.house.EditHousePagerAdapter


class EditHouseholder : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var editHouseHolderPagerAdapter: EditHouseholderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_householder, container, false)
        viewPager = rootView.findViewById(R.id.edit_householder_pager)
        editHouseHolderPagerAdapter = EditHouseholderAdapter(childFragmentManager)

        viewPager.adapter = editHouseHolderPagerAdapter
        return rootView
    }

}