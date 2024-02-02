package com.example.calculatersunotes.ui.edit.rural.house

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R


class EditRuralHouse : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var editHousePagerAdapter: EditRuralHousePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_rural_house, container, false)

        viewPager = rootView.findViewById(R.id.edit_house_container)
        editHousePagerAdapter = EditRuralHousePagerAdapter(childFragmentManager)

        viewPager.adapter = editHousePagerAdapter

        return rootView
    }

}