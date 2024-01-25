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
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.ui.rural.members.AddRuralMember
import com.example.calculatersunotes.ui.urban.members.AddUrbanMember
import com.example.calculatersunotes.utils.AnimationUtil
import com.example.calculatersunotes.utils.FragmentUtil

class HouseDoneCongrats : Fragment() {
    private lateinit var animationUtil: AnimationUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
    private var selectedEnv = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_house_done_congrats, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        animationUtil = AnimationUtil(requireContext())

        val backgroundImage = rootView.findViewById<ImageView>(R.id.background_img)
        val bottomSide = rootView.findViewById<LinearLayout>(R.id.bottom_side)

        //Animate Intro
        animationUtil.makeCongratsAnimation(backgroundImage, bottomSide)

        val startBtn = rootView.findViewById<Button>(R.id.calculate_rsu_btn)
        startBtn.setOnClickListener {
            navigateToResult()
        }

        val addMemberBtn = rootView.findViewById<Button>(R.id.add_member_btn)
        addMemberBtn.setOnClickListener {
            navigateToAddMembers()
        }

        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            selectedEnv = env
        })
        return rootView
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