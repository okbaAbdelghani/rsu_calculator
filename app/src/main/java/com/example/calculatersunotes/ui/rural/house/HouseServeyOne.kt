package com.example.calculatersunotes.ui.rural.house

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil


class HouseServeyOne : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView =  inflater.inflate(R.layout.fragment_house_servey_one, container, false)
        val noHouseTypeBtn = rootView.findViewById<Button>(R.id.no_house_type_btn)
        val yesHouseTypeBtn = rootView.findViewById<Button>(R.id.yes_house_type_btn)
        val noCarBtn = rootView.findViewById<Button>(R.id.no_car_btn)
        val yesCarBtn = rootView.findViewById<Button>(R.id.yes_car_btn)
        val noMotorcycleBtn = rootView.findViewById<Button>(R.id.no_motorcycle_btn)
        val yesMotorcycleBtn = rootView.findViewById<Button>(R.id.yes_motorcycle_btn)

        val parenFragment: RuralHouseFragment? = parentFragment as? RuralHouseFragment

        parenFragment?.let {
            val viewPager: ViewPager? = it.view?.findViewById(R.id.rural_house_pager)

            viewPager?.let {
                val currentItem: Int = it.currentItem
                it.currentItem = currentItem + 1
            }
        }


        fragmentUtil = FragmentUtil(requireContext())

        buttonList = mutableListOf(
            noHouseTypeBtn,
            yesHouseTypeBtn,
            noCarBtn,
            yesCarBtn,
            noMotorcycleBtn,
            yesMotorcycleBtn
        )

        exceptionButtonList = mutableListOf(
            noHouseTypeBtn,
            noCarBtn,
            noMotorcycleBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        noHouseTypeBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noHouseTypeBtn, yesHouseTypeBtn)
            ruralHouseViewModel.updateHouseType(false)
        }

        yesHouseTypeBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesHouseTypeBtn , noHouseTypeBtn)
            ruralHouseViewModel.updateHouseType(true)
        }

        noCarBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noCarBtn , yesCarBtn)
            ruralHouseViewModel.updateCarPossession(false)
        }

        yesCarBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesCarBtn , noCarBtn)
            ruralHouseViewModel.updateCarPossession(true)
        }

        noMotorcycleBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noMotorcycleBtn , yesMotorcycleBtn)
            ruralHouseViewModel.updateCarPossession(false)
        }

        yesMotorcycleBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesMotorcycleBtn , noMotorcycleBtn)
            ruralHouseViewModel.updateCarPossession(true)
        }

        /*
        ruralHouseHolderViewModel.ruralHouseHolder.observe(viewLifecycleOwner) { householder ->
            bool = householder.isCraftsman
            println("this is name: $bool")
        }

         */

        return rootView
    }

}