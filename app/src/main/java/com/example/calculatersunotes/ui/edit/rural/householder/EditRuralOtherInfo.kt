package com.example.calculatersunotes.ui.edit.rural.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentEditOtherInfoBinding
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class EditRuralOtherInfo : Fragment() {
    private var _binding : FragmentEditOtherInfoBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var swipeUtil: SwipeUtil
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private lateinit var editHouseHolderAdapter: EditRuralHouseholderAdapter

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditOtherInfoBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = mutableListOf(
            binding.noCommerceBtn,
            binding.yesCommerceBtn,
            binding.hasHealthSecurityBtn,
            binding.withoutHealthSecurityBtn,
            binding.literacyNoBtn,
            binding.literacyYesBtn,
            binding.highEducationDiplomaNoBtn,
            binding.highEducationDiplomaYesBtn
        )

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->

            val householder = family.householder

            if(householder.isMerchant){
                exceptionButtonList.add(binding.yesCommerceBtn)
            } else {
                exceptionButtonList.add(binding.noCommerceBtn)
            }

            if(householder.hasHealthCoverage){
                exceptionButtonList.add(binding.hasHealthSecurityBtn)
            } else {
                exceptionButtonList.add(binding.withoutHealthSecurityBtn)
            }

            if(householder.isLiterate){
                exceptionButtonList.add(binding.literacyYesBtn)
            } else {
                exceptionButtonList.add(binding.literacyNoBtn)
            }

            if(householder.hasHighEducationDiploma){
                exceptionButtonList.add(binding.highEducationDiplomaYesBtn)
            } else {
                exceptionButtonList.add(binding.highEducationDiplomaNoBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        binding.yesCommerceBtn.setOnClickListener {
            ruralHouseHolderViewModel.isMerchant(true)
            fragmentUtil.toggleTwoOptions(binding.yesCommerceBtn , binding.noCommerceBtn)
        }

        binding.hasHealthSecurityBtn.setOnClickListener {
            ruralHouseHolderViewModel.hasHealthCoverage(true)
            fragmentUtil.toggleTwoOptions(binding.hasHealthSecurityBtn , binding.withoutHealthSecurityBtn)
        }

        binding.withoutHealthSecurityBtn.setOnClickListener {
            ruralHouseHolderViewModel.hasHealthCoverage(false)
            fragmentUtil.toggleTwoOptions(binding.withoutHealthSecurityBtn , binding.hasHealthSecurityBtn)
        }

        binding.literacyNoBtn.setOnClickListener {
            ruralHouseHolderViewModel.setLiteracy(false)
            fragmentUtil.toggleTwoOptions(binding.literacyNoBtn , binding.literacyYesBtn)
        }

        binding.literacyYesBtn.setOnClickListener {
            ruralHouseHolderViewModel.setLiteracy(true)
            fragmentUtil.toggleTwoOptions(binding.literacyYesBtn , binding.literacyNoBtn)
        }

        binding.highEducationDiplomaNoBtn.setOnClickListener {
            ruralHouseHolderViewModel.hasHighEducationDiploma(false)
            fragmentUtil.toggleTwoOptions(binding.highEducationDiplomaNoBtn , binding.highEducationDiplomaYesBtn)
        }

        binding.highEducationDiplomaYesBtn.setOnClickListener {
            ruralHouseHolderViewModel.hasHighEducationDiploma(true)
            fragmentUtil.toggleTwoOptions(binding.highEducationDiplomaYesBtn , binding.highEducationDiplomaNoBtn)
        }

        binding.doneBtn.setOnClickListener {
            ruralHouseHolderViewModel.ruralHouseHolder.observe(viewLifecycleOwner) {householder ->
                ruralFamilyViewModel.updateFamilyHouseHolder(householder)
            }
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())

        }

        binding.backButton.setOnClickListener {
            val parenFragment: EditRuralHouseholder = parentFragment as EditRuralHouseholder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_householder_pager)

            editHouseHolderAdapter = EditRuralHouseholderAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseHolderAdapter)
        }

        return binding.root
    }
}