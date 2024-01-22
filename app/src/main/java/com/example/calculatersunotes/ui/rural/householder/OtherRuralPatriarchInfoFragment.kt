package com.example.calculatersunotes.ui.rural.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.utils.FragmentUtil


class OtherRuralPatriarchInfoFragment : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_other_rural_patriarch_info, container, false)

        fragmentUtil = FragmentUtil(requireContext())

        val noCommerceBtn = view.findViewById<Button>(R.id.no_btn)
        val yesCommerceBtn = view.findViewById<Button>(R.id.yes_btn)
        val hasHealthSecurity = view.findViewById<Button>(R.id.has_health_security_btn)
        val withoutHealthSecurityBtn = view.findViewById<Button>(R.id.without_health_security_btn)
        val noLiteracy = view.findViewById<Button>(R.id.literacy_no_btn)
        val yesLiteracy = view.findViewById<Button>(R.id.literacy_yes_btn)
        val highEducationDiplomaNoBtn = view.findViewById<Button>(R.id.high_education_diploma_no_btn)
        val highEducationDiplomaYesBtn = view.findViewById<Button>(R.id.high_education_diploma_yes_btn)

        buttonList = mutableListOf(
            noCommerceBtn,
            yesCommerceBtn,
            hasHealthSecurity,
            withoutHealthSecurityBtn,
            noLiteracy,
            yesLiteracy,
            highEducationDiplomaNoBtn,
            highEducationDiplomaYesBtn
        )

        exceptionButtonList = mutableListOf(
            noCommerceBtn,
            noLiteracy,
            highEducationDiplomaNoBtn,
            withoutHealthSecurityBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        noCommerceBtn.setOnClickListener {
            ruralHouseHolderViewModel.isMerchant(false)
            fragmentUtil.toggleTwoOptions(noCommerceBtn, yesCommerceBtn)
        }

        yesCommerceBtn.setOnClickListener {
            ruralHouseHolderViewModel.isMerchant(true)
            fragmentUtil.toggleTwoOptions(yesCommerceBtn , noCommerceBtn)
        }

        hasHealthSecurity.setOnClickListener {
            ruralHouseHolderViewModel.hasHealthCoverage(true)
            fragmentUtil.toggleTwoOptions(hasHealthSecurity , withoutHealthSecurityBtn)
        }

        withoutHealthSecurityBtn.setOnClickListener {
            ruralHouseHolderViewModel.hasHealthCoverage(false)
            fragmentUtil.toggleTwoOptions(withoutHealthSecurityBtn , hasHealthSecurity)
        }

        noLiteracy.setOnClickListener {
            ruralHouseHolderViewModel.setLiteracy(false)
            fragmentUtil.toggleTwoOptions(noLiteracy , yesLiteracy)
        }

        yesLiteracy.setOnClickListener {
            ruralHouseHolderViewModel.setLiteracy(true)
            fragmentUtil.toggleTwoOptions(yesLiteracy , noLiteracy)
        }

        highEducationDiplomaNoBtn.setOnClickListener {
            ruralHouseHolderViewModel.hasHighEducationDiploma(false)
            fragmentUtil.toggleTwoOptions(highEducationDiplomaNoBtn , highEducationDiplomaYesBtn)
        }

        highEducationDiplomaYesBtn.setOnClickListener {
            ruralHouseHolderViewModel.hasHighEducationDiploma(true)
            fragmentUtil.toggleTwoOptions(highEducationDiplomaYesBtn , highEducationDiplomaNoBtn)
        }
        // Inflate the layout for this fragment

        return view
    }



}