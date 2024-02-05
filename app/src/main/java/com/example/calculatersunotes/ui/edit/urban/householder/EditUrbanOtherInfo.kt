package com.example.calculatersunotes.ui.edit.urban.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.urban.householder.UrbanHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class EditUrbanOtherInfo : Fragment() {
    private var buttonList: MutableList<Button> = mutableListOf()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var editHouseHolderAdapter: EditUrbanHouseHolderAdapter
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_urban_other_info, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val doneBtn = rootView.findViewById<Button>(R.id.done_btn)
        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        val isRetiredOrBeneficiaryOfIncomeBtn = rootView.findViewById<Button>(R.id.is_retired_or_beneficiary_Of_income_btn)
        val withoutIncomeBtn = rootView.findViewById<Button>(R.id.without_income_btn)
        val hasHealthSecurityBtn = rootView.findViewById<Button>(R.id.has_health_security_btn)
        val withoutHealthSecurityBtn = rootView.findViewById<Button>(R.id.without_health_security_btn)
        val hasDiplomaBtn = rootView.findViewById<Button>(R.id.has_diploma_btn)
        val withoutDiplomaBtn = rootView.findViewById<Button>(R.id.without_diploma_btn)
        val machineryManagementYesBtn = rootView.findViewById<Button>(R.id.machinery_management_yes_btn)
        val machineryManagementNoBtn = rootView.findViewById<Button>(R.id.machinery_management_no_btn)

        buttonList = mutableListOf(
            isRetiredOrBeneficiaryOfIncomeBtn,
            withoutIncomeBtn,
            hasHealthSecurityBtn,
            withoutHealthSecurityBtn,
            hasDiplomaBtn,
            withoutDiplomaBtn,
            machineryManagementYesBtn,
            machineryManagementNoBtn
        )

        urbanFamilyViewModel.family.observe(viewLifecycleOwner) {family ->
            val householder = family.householder

            if(householder.isRetiredOrBeneficiaryOfIncome) {
                exceptionButtonList.add(isRetiredOrBeneficiaryOfIncomeBtn)
            } else {
                exceptionButtonList.add(withoutIncomeBtn)
            }

            if(householder.hasHealthCoverage) {
                exceptionButtonList.add(hasHealthSecurityBtn)
            } else {
                exceptionButtonList.add(withoutHealthSecurityBtn)
            }

            if(householder.hasDiploma) {
                exceptionButtonList.add(hasDiplomaBtn)
            } else {
                exceptionButtonList.add(withoutDiplomaBtn)
            }

            if(householder.equipmentManagementActivity) {
                exceptionButtonList.add(machineryManagementYesBtn)
            } else {
                exceptionButtonList.add(machineryManagementNoBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        isRetiredOrBeneficiaryOfIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(true)
            fragmentUtil.toggleTwoOptions(isRetiredOrBeneficiaryOfIncomeBtn , withoutIncomeBtn)
        }

        withoutIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(false)
            fragmentUtil.toggleTwoOptions(withoutIncomeBtn , isRetiredOrBeneficiaryOfIncomeBtn)
        }

        hasHealthSecurityBtn.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(true)
            fragmentUtil.toggleTwoOptions(hasHealthSecurityBtn , withoutHealthSecurityBtn)
        }

        withoutHealthSecurityBtn.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(false)
            fragmentUtil.toggleTwoOptions(withoutHealthSecurityBtn , hasHealthSecurityBtn)
        }

        hasDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(true)
            fragmentUtil.toggleTwoOptions(hasDiplomaBtn , withoutDiplomaBtn)
        }

        withoutDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(false)
            fragmentUtil.toggleTwoOptions(withoutDiplomaBtn , hasDiplomaBtn)
        }

        machineryManagementYesBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(true)
            fragmentUtil.toggleTwoOptions(machineryManagementYesBtn , machineryManagementNoBtn)
        }

        machineryManagementNoBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(false)
            fragmentUtil.toggleTwoOptions(machineryManagementNoBtn , machineryManagementYesBtn)
        }

        doneBtn.setOnClickListener {
            urbanHouseHolderViewModel.urbanHouseHolder.observe(viewLifecycleOwner) {householder ->
                urbanFamilyViewModel.updateFamilyHouseHolder(householder)
            }
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }


        backBtn.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseHolderAdapter)
        }

        return rootView
    }

}