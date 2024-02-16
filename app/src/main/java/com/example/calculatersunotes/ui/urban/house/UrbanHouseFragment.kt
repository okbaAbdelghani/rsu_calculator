package com.example.calculatersunotes.ui.urban.house

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.databinding.FragmentUrbanHouseBinding

class UrbanHouseFragment : Fragment() {
    private var _binding : FragmentUrbanHouseBinding? = null
    private lateinit var viewPager: ViewPager
    private lateinit var urbanHousePagerAdapter: UrbanHousePagerAdapter

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUrbanHouseBinding.inflate(inflater, container, false)

        viewPager = binding.urbanHousePager
        urbanHousePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)
        viewPager.adapter = urbanHousePagerAdapter

        return binding.root
    }

}