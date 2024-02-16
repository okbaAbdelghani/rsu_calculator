package com.example.calculatersunotes.ui.edit.rural.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.databinding.FragmentEditRuralHouseholderBinding


class EditRuralHouseholder : Fragment() {
    private var _binding : FragmentEditRuralHouseholderBinding? = null

    private lateinit var viewPager: ViewPager
    private lateinit var editHouseHolderPagerAdapter: EditRuralHouseholderAdapter

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditRuralHouseholderBinding.inflate(inflater, container, false)

        viewPager = binding.editHouseholderPager
        editHouseHolderPagerAdapter = EditRuralHouseholderAdapter(childFragmentManager)

        viewPager.adapter = editHouseHolderPagerAdapter

        return binding.root
    }

}