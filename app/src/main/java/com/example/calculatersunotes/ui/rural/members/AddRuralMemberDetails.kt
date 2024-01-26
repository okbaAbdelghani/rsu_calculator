package com.example.calculatersunotes.ui.rural.members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.utils.FragmentUtil


class AddRuralMemberDetails : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralMemberViewModel: RuralMemberViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_add_rural_member_details, container, false)

        val noLiteracyBtn = rootView.findViewById<Button>(R.id.no_literacy_btn)
        val yesLiteracyBtn = rootView.findViewById<Button>(R.id.yes_literacy_btn)
        val noHighEducationBtn = rootView.findViewById<Button>(R.id.no_high_education_btn)
        val yesHighEducationBtn = rootView.findViewById<Button>(R.id.yes_high_education_btn)
        val yesCommerceBtn = rootView.findViewById<Button>(R.id.yes_commerce_btn)
        val noCommerceBtn = rootView.findViewById<Button>(R.id.no_commerce_btn)

        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        fragmentUtil = FragmentUtil(requireContext())

        buttonList = mutableListOf(
            noLiteracyBtn,
            yesLiteracyBtn,
            noHighEducationBtn,
            yesHighEducationBtn,
            yesCommerceBtn,
            noCommerceBtn
        )

        exceptionButtonList = mutableListOf(
            noLiteracyBtn,
            noHighEducationBtn,
            noCommerceBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        noLiteracyBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noLiteracyBtn, yesLiteracyBtn)
            ruralMemberViewModel.updateLiteracyStatus(false)
        }

        yesLiteracyBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesLiteracyBtn, noLiteracyBtn)
            ruralMemberViewModel.updateLiteracyStatus(true)
        }

        noHighEducationBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noHighEducationBtn, yesHighEducationBtn)
            ruralMemberViewModel.updateHighEducationDiploma(false)
        }

        yesHighEducationBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesHighEducationBtn, noHighEducationBtn)
            ruralMemberViewModel.updateHighEducationDiploma(true)
        }

        yesCommerceBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesCommerceBtn, noCommerceBtn)
            ruralMemberViewModel.isMerchant(true)
        }

        noCommerceBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noCommerceBtn, yesCommerceBtn)
            ruralMemberViewModel.isMerchant(false)
        }

        nextBtn.setOnClickListener {
            val familyMember = ruralMemberViewModel.ruralMember.value
            if (familyMember != null) {
                ruralFamilyViewModel.addFamilyMember(familyMember)
                ruralFamilyViewModel.createSurveyItems(requireContext())
                navigateToResult()
            }
        }

        return rootView
    }

    private fun navigateToResult(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, ResultFragment())
    }

}