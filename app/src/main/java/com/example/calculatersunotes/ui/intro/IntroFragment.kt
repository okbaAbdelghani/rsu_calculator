package com.example.calculatersunotes.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentIntroBinding
import com.example.calculatersunotes.utils.AnimationUtil

class IntroFragment : Fragment() {
    private var _binding : FragmentIntroBinding? = null

    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var animationUtil: AnimationUtil

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIntroBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        animationUtil = AnimationUtil(requireContext())

        //Animate Intro
        animationUtil.makeCongratsAnimation(binding.backgroundImg, binding.bottomSide)

        //start
        binding.startBtn.setOnClickListener {
            navigateToEnvironments()
        }

        return binding.root
    }

    private fun navigateToEnvironments(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, EnvRegionContainerFrag())
    }
}