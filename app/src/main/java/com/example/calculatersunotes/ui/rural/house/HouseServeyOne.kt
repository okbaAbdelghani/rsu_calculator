package com.example.calculatersunotes.ui.rural.house

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil


class HouseServeyOne : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val sharedViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
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

        var environment = ""
        var bool = false

        sharedViewModel.family.observe(viewLifecycleOwner) { family ->
            environment = family.environment
            println("this is env: $environment")
        }

        ruralHouseHolderViewModel.ruralHouseHolder.observe(viewLifecycleOwner) { householder ->
            bool = householder.isCraftsman
            println("this is name: $bool")
        }

        return rootView
    }

}