package com.example.calculatersunotes.ui.edit.householder

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
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.intro.EnvRegionContainerFrag
import com.example.calculatersunotes.ui.intro.EnvRegionPagerAdapter
import com.example.calculatersunotes.ui.rural.house.HousePagerAdapter
import com.example.calculatersunotes.ui.rural.house.RuralHouseFragment
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class EditOtherInfo : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var swipeUtil: SwipeUtil
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private lateinit var editHouseHolderAdapter: EditHouseholderAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_other_info, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val doneBtn = view.findViewById<Button>(R.id.done_btn)
        val backBtn = view.findViewById<ImageButton>(R.id.back_button)

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


        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->

            val householder = family.householder

            if(householder.isMerchant){
                exceptionButtonList.add(yesCommerceBtn)
            } else {
                exceptionButtonList.add(noCommerceBtn)
            }

            if(householder.hasHealthCoverage){
                exceptionButtonList.add(hasHealthSecurity)
            } else {
                exceptionButtonList.add(withoutHealthSecurityBtn)
            }

            if(householder.isLiterate){
                exceptionButtonList.add(yesLiteracy)
            } else {
                exceptionButtonList.add(noLiteracy)
            }

            if(householder.hasHighEducationDiploma){
                exceptionButtonList.add(highEducationDiplomaYesBtn)
            } else {
                exceptionButtonList.add(highEducationDiplomaNoBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

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

        doneBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }

        backBtn.setOnClickListener {
            val parenFragment: EditHouseholder = parentFragment as EditHouseholder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_householder_pager)

            editHouseHolderAdapter = EditHouseholderAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseHolderAdapter)
        }

        return view
    }
}