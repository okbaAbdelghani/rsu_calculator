package com.example.calculatersunotes.ui.urban

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.databinding.FragmentUrbanBinding


class Urban : Fragment() {
    private var _binding: FragmentUrbanBinding? = null

    private lateinit var viewPager: ViewPager
    private lateinit var urbanPagerAdapter: UrbanPagerAdapter

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUrbanBinding.inflate(inflater, container, false)

        viewPager = binding.questionsContainer
        urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
        viewPager.adapter = urbanPagerAdapter

        return binding.root
    }

}