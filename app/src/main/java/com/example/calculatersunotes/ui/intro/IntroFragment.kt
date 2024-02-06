package com.example.calculatersunotes.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.intro.environments.EnvironmentsFragment
import com.example.calculatersunotes.utils.AnimationUtil

class IntroFragment : Fragment() {

    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var animationUtil: AnimationUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_intro, container, false)


        fragmentUtil = FragmentUtil(requireContext())
        animationUtil = AnimationUtil(requireContext())

        val backgroundImage = rootView.findViewById<ImageView>(R.id.background_img)
        val bottomSide = rootView.findViewById<LinearLayout>(R.id.bottom_side)

        //Animate Intro
        animationUtil.makeCongratsAnimation(backgroundImage, bottomSide)

        //start
        val startBtn = rootView.findViewById<Button>(R.id.start_btn)
        startBtn.setOnClickListener {
            navigateToEnvironments()
        }

        return rootView
    }

    private fun navigateToEnvironments(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, EnvRegionContainerFrag())
    }
}