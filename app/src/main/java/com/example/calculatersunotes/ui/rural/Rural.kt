package com.example.calculatersunotes.ui.rural

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.congrats.HouseHolderDoneCongrats
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil

class Rural : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var mainPagerAdapter: RuralPagerAdapter
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private val sharedViewModel: RuralFamilyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_rural, container, false)

        viewPager = rootView.findViewById<ViewPager>(R.id.questions_container)
        mainPagerAdapter = RuralPagerAdapter(childFragmentManager)
        viewPager.adapter = mainPagerAdapter

        fragmentUtil = FragmentUtil(requireContext())
        onNexBtnClicked(rootView)
        return rootView
    }

    private fun onNexBtnClicked(view: View) {
        val nxtBtn = view.findViewById<ImageButton>(R.id.next_btn);
        nxtBtn.setOnClickListener {
            if(viewPager.currentItem == mainPagerAdapter.count - 1){
                setFamilyHouseHolder()
                navigateToHouseHolderCongrats()
            }
            if (viewPager.currentItem < mainPagerAdapter.count){
                viewPager.setCurrentItem(viewPager.currentItem + 1, true)
            }

        }
    }

    private fun navigateToHouseHolderCongrats(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, HouseHolderDoneCongrats())
    }

    private fun setFamilyHouseHolder(){
        ruralHouseHolderViewModel.ruralHouseHolder.observe(viewLifecycleOwner) { houseHolder ->
            sharedViewModel.updateFamilyHouseHolder(houseHolder)
        }
    }

}