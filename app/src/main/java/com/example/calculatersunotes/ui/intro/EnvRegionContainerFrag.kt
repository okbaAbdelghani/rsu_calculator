package com.example.calculatersunotes.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.congrats.HouseHolderDoneCongrats
import com.example.calculatersunotes.ui.onboards.OnboardingPagerAdapter
import com.example.calculatersunotes.ui.rural.Rural
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class EnvRegionContainerFrag : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var envRegionPagerAdapter: EnvRegionPagerAdapter
    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var swipeUtil: SwipeUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_env_region_container, container, false)
        viewPager = rootView.findViewById(R.id.viewPager)
        envRegionPagerAdapter = EnvRegionPagerAdapter(childFragmentManager)
        viewPager.adapter = envRegionPagerAdapter
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        onNexBtnClicked(rootView);


        return rootView
    }

    private fun onNexBtnClicked(view: View) {
        val nxtBtn = view.findViewById<ImageButton>(R.id.next_btn);
        nxtBtn.setOnClickListener {
            /*
            when (selectedEnv) {
                "urban" -> fragmentUtil.replaceFragment(
                    requireActivity().supportFragmentManager,
                    R.id.fragmentContainer,
                    Urban()
                )

                "rural" -> fragmentUtil.replaceFragment(
                    requireActivity().supportFragmentManager,
                    R.id.fragmentContainer,
                    Rural()
                )
                else -> {
                    Toast.makeText(requireContext(),"Env Required", Toast.LENGTH_SHORT).show()
                }
            }
             */
            swipeUtil.navigateNext(viewPager, envRegionPagerAdapter, navigateToRural)

        }
    }

    private val navigateToRural: () -> Unit = {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Rural())
    }

}