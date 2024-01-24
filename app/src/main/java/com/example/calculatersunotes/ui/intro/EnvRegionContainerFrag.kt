package com.example.calculatersunotes.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
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
        val rootView = inflater.inflate(R.layout.fragment_env_region_container, container, false)
        viewPager = rootView.findViewById(R.id.viewPager)
        envRegionPagerAdapter = EnvRegionPagerAdapter(childFragmentManager)
        viewPager.adapter = envRegionPagerAdapter
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val nxtBtn = rootView.findViewById<ImageButton>(R.id.next_btn);
        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            if (env != ""){
                nxtBtn.isEnabled = true
                nxtBtn.alpha = 1.0f
            } else {
                nxtBtn.isEnabled = false
                nxtBtn.alpha = 0.5f
            }
            selectedEnv = env
        })

        onNexBtnClicked(rootView);


        return rootView
    }

    private fun onNexBtnClicked(view: View) {
        val nxtBtn = view.findViewById<ImageButton>(R.id.next_btn);
        nxtBtn.setOnClickListener {

            swipeUtil.navigateNext(viewPager, envRegionPagerAdapter, navigateToRuralOrUrban)

        }
    }

    private val navigateToRuralOrUrban: () -> Unit = {
        when (selectedEnv) {
            "rural" ->  fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Rural())
            "urban" ->  fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Urban())
            "" -> Toast.makeText(requireContext(),"environment required!", Toast.LENGTH_SHORT).show()
        }


    }

}