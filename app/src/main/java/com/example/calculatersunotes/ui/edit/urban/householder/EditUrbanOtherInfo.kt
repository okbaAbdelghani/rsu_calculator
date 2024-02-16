package com.example.calculatersunotes.ui.edit.urban.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentEditUrbanOtherInfoBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.urban.householder.UrbanHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class EditUrbanOtherInfo : Fragment() {
    private var _binding : FragmentEditUrbanOtherInfoBinding? = null

    private var buttonList: MutableList<Button> = mutableListOf()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var editHouseHolderAdapter: EditUrbanHouseHolderAdapter
    private var exceptionButtonList: MutableList<Button> = mutableListOf()

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditUrbanOtherInfoBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = mutableListOf(
            binding.isRetiredOrBeneficiaryOfIncomeBtn,
            binding.withoutIncomeBtn,
            binding.hasHealthSecurityBtn,
            binding.withoutHealthSecurityBtn,
            binding.hasDiplomaBtn,
            binding.withoutDiplomaBtn,
            binding.machineryManagementYesBtn,
            binding.machineryManagementNoBtn
        )

        urbanFamilyViewModel.family.observe(viewLifecycleOwner) {
            val householder = it.householder

            if(householder.isRetiredOrBeneficiaryOfIncome) {
                exceptionButtonList.add(binding.isRetiredOrBeneficiaryOfIncomeBtn)
            } else {
                exceptionButtonList.add(binding.withoutIncomeBtn)
            }

            if(householder.hasHealthCoverage) {
                exceptionButtonList.add(binding.hasHealthSecurityBtn)
            } else {
                exceptionButtonList.add(binding.withoutHealthSecurityBtn)
            }

            if(householder.hasDiploma) {
                exceptionButtonList.add(binding.hasDiplomaBtn)
            } else {
                exceptionButtonList.add(binding.withoutDiplomaBtn)
            }

            if(householder.equipmentManagementActivity) {
                exceptionButtonList.add(binding.machineryManagementYesBtn)
            } else {
                exceptionButtonList.add(binding.machineryManagementNoBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        binding.isRetiredOrBeneficiaryOfIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(true)
            fragmentUtil.toggleTwoOptions(binding.isRetiredOrBeneficiaryOfIncomeBtn , binding.withoutIncomeBtn)
        }

        binding.withoutIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(false)
            fragmentUtil.toggleTwoOptions(binding.withoutIncomeBtn , binding.isRetiredOrBeneficiaryOfIncomeBtn)
        }

        binding.hasHealthSecurityBtn.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(true)
            fragmentUtil.toggleTwoOptions(binding.hasHealthSecurityBtn , binding.withoutHealthSecurityBtn)
        }

        binding.withoutHealthSecurityBtn.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(false)
            fragmentUtil.toggleTwoOptions(binding.withoutHealthSecurityBtn , binding.hasHealthSecurityBtn)
        }

        binding.hasDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(true)
            fragmentUtil.toggleTwoOptions(binding.hasDiplomaBtn , binding.withoutDiplomaBtn)
        }

        binding.withoutDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(false)
            fragmentUtil.toggleTwoOptions(binding.withoutDiplomaBtn , binding.hasDiplomaBtn)
        }

        binding.machineryManagementYesBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(true)
            fragmentUtil.toggleTwoOptions(binding.machineryManagementYesBtn , binding.machineryManagementNoBtn)
        }

        binding.machineryManagementNoBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(false)
            fragmentUtil.toggleTwoOptions(binding.machineryManagementNoBtn , binding.machineryManagementYesBtn)
        }

        binding.doneBtn.setOnClickListener {
            urbanHouseHolderViewModel.urbanHouseHolder.observe(viewLifecycleOwner) {householder ->
                urbanFamilyViewModel.updateFamilyHouseHolder(householder)
            }
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }


        binding.backButton.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseHolderAdapter)
        }

        return binding.root
    }

}