package com.example.calculatersunotes.ui.urban.householder

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
import com.example.calculatersunotes.ui.congrats.HouseHolderDoneCongrats
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class OtherUrbanPatriarchInfoFragment : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private val urbanHouseViewModel: UrbanFamilyViewModel by activityViewModels()
    private lateinit var urbanPagerAdapter: UrbanPagerAdapter
    private lateinit var swipeUtil: SwipeUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_other_urban_patriarch_info, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        val isRetiredOrBeneficiaryOfIncomeBtn = rootView.findViewById<Button>(R.id.is_retired_or_beneficiary_Of_income_btn)
        val withoutIncomeBtn = rootView.findViewById<Button>(R.id.without_income_btn)
        val withoutHealthSecurityBtn = rootView.findViewById<Button>(R.id.without_health_security_btn)
        val hasHealthSecurity = rootView.findViewById<Button>(R.id.has_health_security_btn)
        val hasDiplomaBtn = rootView.findViewById<Button>(R.id.has_diploma_btn)
        val withoutDiplomaBtn = rootView.findViewById<Button>(R.id.without_diploma_btn)
        val machineryManagementYesBtn = rootView.findViewById<Button>(R.id.machinery_management_yes_btn)
        val machineryManagementNoBtn = rootView.findViewById<Button>(R.id.machinery_management_no_btn)

        buttonList = mutableListOf(
            isRetiredOrBeneficiaryOfIncomeBtn,
            withoutIncomeBtn,
            hasHealthSecurity,
            withoutHealthSecurityBtn,
            hasDiplomaBtn,
            withoutDiplomaBtn,
            machineryManagementYesBtn,
            machineryManagementNoBtn
        )

        exceptionButtonList = mutableListOf(
            withoutIncomeBtn,
            withoutDiplomaBtn,
            machineryManagementNoBtn,
            withoutHealthSecurityBtn
        )

        isRetiredOrBeneficiaryOfIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(true)
            fragmentUtil.toggleTwoOptions(isRetiredOrBeneficiaryOfIncomeBtn, withoutIncomeBtn)
        }

        withoutIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(false)
            fragmentUtil.toggleTwoOptions(withoutIncomeBtn, isRetiredOrBeneficiaryOfIncomeBtn)
        }

        hasDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(true)
            fragmentUtil.toggleTwoOptions(hasDiplomaBtn, withoutDiplomaBtn)
        }

        withoutDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(false)
            fragmentUtil.toggleTwoOptions(withoutDiplomaBtn, hasDiplomaBtn)
        }

        hasHealthSecurity.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(true)
            fragmentUtil.toggleTwoOptions(hasHealthSecurity, withoutHealthSecurityBtn)
        }

        withoutHealthSecurityBtn.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(false)
            fragmentUtil.toggleTwoOptions(withoutHealthSecurityBtn, hasHealthSecurity)
        }

        machineryManagementYesBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(true)
            fragmentUtil.toggleTwoOptions(machineryManagementYesBtn, machineryManagementNoBtn)
        }

        machineryManagementNoBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(false)
            fragmentUtil.toggleTwoOptions(machineryManagementNoBtn, machineryManagementYesBtn)
        }

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        swipeBack(backBtn)
        onNexBtnClicked(rootView)

        return rootView
    }

    private fun swipeBack(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, urbanPagerAdapter)
        }
    }

    private fun onNexBtnClicked(view: View) {
        val nxtBtn = view.findViewById<ImageButton>(R.id.next_btn);
        nxtBtn.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, urbanPagerAdapter, navigateToHouseHolderCongrats)
            if (viewPager != null) {
                if(viewPager.currentItem == urbanPagerAdapter.count - 1) {
                    setFamilyHouseHolder()
                }
            }
        }
    }

    private val navigateToHouseHolderCongrats : () -> Unit = {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, HouseHolderDoneCongrats())
    }

    private fun setFamilyHouseHolder() {
        urbanHouseHolderViewModel.urbanHouseHolder.observe(viewLifecycleOwner) { houseHolder ->
            urbanHouseViewModel.updateFamilyHouseHolder(houseHolder)
        }
    }

}