package com.example.calculatersunotes.ui.edit.urban.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.databinding.FragmentEditUrbanHouseHolderBinding


class EditUrbanHouseHolder : Fragment() {
    private var _binding : FragmentEditUrbanHouseHolderBinding? = null

    private lateinit var viewPager: ViewPager
    private lateinit var editHouseHolderPagerAdapter: EditUrbanHouseHolderAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditUrbanHouseHolderBinding.inflate(inflater, container, false)

        viewPager = binding.editUrbanHouseholderPager
        editHouseHolderPagerAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)

        viewPager.adapter = editHouseHolderPagerAdapter

        return binding.root
    }

}
