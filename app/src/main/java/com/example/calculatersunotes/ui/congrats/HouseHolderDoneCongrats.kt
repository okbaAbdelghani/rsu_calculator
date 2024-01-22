package com.example.calculatersunotes.ui.congrats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.intro.EnvRegionContainerFrag
import com.example.calculatersunotes.ui.rural.house.RuralHouseFragment
import com.example.calculatersunotes.utils.AnimationUtil
import com.example.calculatersunotes.utils.FragmentUtil

class HouseHolderDoneCongrats : Fragment() {
    private lateinit var animationUtil: AnimationUtil
    private lateinit var fragmentUtil: FragmentUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_house_holder_done_congrats, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        animationUtil = AnimationUtil(requireContext())

        val backgroundImage = rootView.findViewById<ImageView>(R.id.background_img)
        val bottomSide = rootView.findViewById<LinearLayout>(R.id.bottom_side)

        //Animate Intro
        animationUtil.makeCongratsAnimation(backgroundImage, bottomSide)

        val startBtn = rootView.findViewById<Button>(R.id.start_home_btn)
        startBtn.setOnClickListener {
            navigateToHousePart()
        }

        return rootView
    }

    private fun navigateToHousePart(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, RuralHouseFragment())
    }
}