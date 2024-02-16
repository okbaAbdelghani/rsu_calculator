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
import androidx.lifecycle.Observer
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentHouseDoneCongratsBinding
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.ui.rural.members.AddRuralMember
import com.example.calculatersunotes.ui.urban.members.AddUrbanMember
import com.example.calculatersunotes.utils.AnimationUtil
import com.example.calculatersunotes.utils.FragmentUtil

class HouseDoneCongrats : Fragment() {
    private var _binding : FragmentHouseDoneCongratsBinding? = null

    private lateinit var animationUtil: AnimationUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
    private var selectedEnv = ""

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHouseDoneCongratsBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        animationUtil = AnimationUtil(requireContext())

        //Animate Intro
        animationUtil.makeCongratsAnimation(binding.backgroundImg, binding.bottomSide)

        binding.calculateRsuBtn.setOnClickListener {
            navigateToResult()
        }

        binding.addMemberBtn.setOnClickListener {
            navigateToAddMembers()
        }

        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            selectedEnv = env
        })
        return binding.root
    }

    private fun navigateToResult(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, ResultFragment())
    }

    private fun navigateToAddMembers() {
        if(selectedEnv == "rural"){
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddRuralMember())
        }

        if(selectedEnv == "urban"){
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMember())
        }
    }

}