package com.example.calculatersunotes.ui.urban.members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.data.models.UrbanMember
import com.example.calculatersunotes.databinding.FragmentAddUrbanMemberMinusBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.utils.FragmentUtil

class AddUrbanMemberMinus : Fragment() {
    private var _binding : FragmentAddUrbanMemberMinusBinding? = null

    private lateinit var fragmentUtil: FragmentUtil
    private val urbanMemberViewModel: UrbanMemberViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private var buttonList: List<Button> = mutableListOf()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUtil = FragmentUtil(requireContext())
        _binding = FragmentAddUrbanMemberMinusBinding.inflate(inflater, container, false)

        buttonList = mutableListOf(
            binding.noSchoolBtn,
            binding.yesSchoolBtn,
            binding.publicBtn,
            binding.privateBtn,
            binding.childBtn,
            binding.partnerBtn,
            binding.otherRelationshipBtn
        )

        exceptionButtonList = mutableListOf(
            binding.noSchoolBtn,
            binding.privateBtn,
            binding.otherRelationshipBtn
        )

        val relationshipBtns = mutableListOf(
            binding.childBtn,
            binding.partnerBtn,
            binding.otherRelationshipBtn
        )

        binding.childBtn.setOnClickListener {
            urbanMemberViewModel.updateRelationship("child")
            fragmentUtil.setInactiveButtonColors(relationshipBtns,  binding.childBtn)
        }

        binding.partnerBtn.setOnClickListener {
            urbanMemberViewModel.updateRelationship("partner")
            fragmentUtil.setInactiveButtonColors(relationshipBtns, binding.partnerBtn)
        }

        binding.otherRelationshipBtn.setOnClickListener {
            urbanMemberViewModel.updateRelationship("other")
            fragmentUtil.setInactiveButtonColors(relationshipBtns, binding.otherRelationshipBtn)
        }

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        binding.yesSchoolBtn.setOnClickListener {
            urbanMemberViewModel.updateSchooling(true)
            fragmentUtil.toggleTwoOptions( binding.yesSchoolBtn,  binding.noSchoolBtn)
        }

        binding.noSchoolBtn.setOnClickListener {
            urbanMemberViewModel.updateSchooling(false)
            fragmentUtil.toggleTwoOptions(binding.noSchoolBtn, binding.yesSchoolBtn)
        }

        binding.publicBtn.setOnClickListener {
            urbanMemberViewModel.updateSchoolType("public")
            fragmentUtil.toggleTwoOptions(binding.publicBtn, binding.privateBtn)
        }

            binding.privateBtn.setOnClickListener {
            urbanMemberViewModel.updateSchoolType("private")
            fragmentUtil.toggleTwoOptions(binding.privateBtn, binding.publicBtn)
        }

        binding.backButton.setOnClickListener{
            navigateToAddMember()
        }

        binding.doneBtn.setOnClickListener {
            urbanMemberViewModel.urbanMember.observe(viewLifecycleOwner) {member ->
                val urbanMember = UrbanMember(
                    member.hasDiploma,
                    member.isSchooler,
                    member.privateSector,
                    member.publicSector,
                    member.hasAJob,
                    member.equipmentManagementActivity,
                    member.hasHighProfessionalPosition,
                    member.fullName,
                    member.hasHealthCoverage,
                    member.isChildOfHouseHolder,
                    member.age,
                    member.isPartnerOfHouseHolder
                )

                urbanFamilyViewModel.addFamilyMember(urbanMember)
                println(member.fullName)
            }
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }
        return binding.rootView
    }

    private fun navigateToAddMember() {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMember(), "left")
    }

}