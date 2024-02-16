package com.example.calculatersunotes.ui.edit.rural.house

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentEditRuralHouseBinding


class EditRuralHouse : Fragment() {
    private var _binding : FragmentEditRuralHouseBinding? = null

    private lateinit var viewPager: ViewPager
    private lateinit var editHousePagerAdapter: EditRuralHousePagerAdapter

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditRuralHouseBinding.inflate(inflater, container, false)

        viewPager = binding.editHouseContainer
        editHousePagerAdapter = EditRuralHousePagerAdapter(childFragmentManager)

        viewPager.adapter = editHousePagerAdapter

        return binding.root
    }

}