package com.example.calculatersunotes.ui.congrats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentHouseHolderDoneCongratsBinding
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.rural.house.RuralHouseFragment
import com.example.calculatersunotes.ui.urban.house.UrbanHouseFragment
import com.example.calculatersunotes.utils.AnimationUtil
import com.example.calculatersunotes.utils.FragmentUtil

class HouseHolderDoneCongrats : Fragment() {
    private var _binding : FragmentHouseHolderDoneCongratsBinding? = null

    private lateinit var animationUtil: AnimationUtil
    private lateinit var fragmentUtil: FragmentUtil
    private  var env = ""
    private val environmentViewModel : EnvironmentViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHouseHolderDoneCongratsBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        animationUtil = AnimationUtil(requireContext())

        //Animate Intro
        animationUtil.makeCongratsAnimation(binding.backgroundImg, binding.bottomSide)

        environmentViewModel.environment.observe(viewLifecycleOwner) {environment ->
            env = environment
        }
        binding.startHomeBtn.setOnClickListener {

            if(env == "urban") {
                navigateToUrbanHousePart()
            }

            if(env == "rural") {
                navigateToRuralHousePart()
            }
        }

        return binding.root
    }

    private fun navigateToRuralHousePart(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, RuralHouseFragment())
    }

    private fun navigateToUrbanHousePart(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, UrbanHouseFragment())
    }
}