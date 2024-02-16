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
import com.example.calculatersunotes.databinding.FragmentEnvRegionContainerBinding
import com.example.calculatersunotes.databinding.FragmentRegionsBinding
import com.example.calculatersunotes.ui.base.ConditionalViewPager
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.congrats.HouseHolderDoneCongrats
import com.example.calculatersunotes.ui.onboards.OnboardingPagerAdapter
import com.example.calculatersunotes.ui.rural.Rural
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class EnvRegionContainerFrag : Fragment() {
    private var _binding : FragmentEnvRegionContainerBinding? = null

    private lateinit var viewPager: ConditionalViewPager
    private lateinit var envRegionPagerAdapter: EnvRegionPagerAdapter
    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var swipeUtil: SwipeUtil
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
        _binding = FragmentEnvRegionContainerBinding.inflate(inflater, container, false)

        viewPager = binding.viewPager
        envRegionPagerAdapter = EnvRegionPagerAdapter(childFragmentManager)
        viewPager.adapter = envRegionPagerAdapter
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        viewPager.setSwipeEnabled(false)

        if (viewPager.currentItem == 0) {
            binding.includedBackButton.backBtn.visibility = View.GONE
        }

        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            if (env != ""){
                binding.includedNext.nextBtn.isEnabled = true
                binding.includedNext.nextBtn.alpha = 1.0f
                viewPager.setSwipeEnabled(true)
            } else {
                binding.includedNext.nextBtn.isEnabled = false
                binding.includedNext.nextBtn.alpha = 0.5f
            }

            selectedEnv = env
        })

        onNexBtnClicked(binding.root);
        onBackBtnClicked(binding.root);

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if(position == 0) {
                    binding.includedBackButton.backBtn.visibility = View.GONE
                } else {
                    binding.includedBackButton.backBtn.visibility = View.VISIBLE
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        return binding.root
    }

    private fun onNexBtnClicked(view: View) {
        binding.includedNext.nextBtn.setOnClickListener {
            swipeUtil.navigateNext(viewPager, envRegionPagerAdapter, navigateToRuralOrUrban)
        }
    }
    private fun onBackBtnClicked(view: View) {
        binding.includedBackButton.backBtn.setOnClickListener {
            swipeUtil.navigateBack(viewPager, envRegionPagerAdapter)
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