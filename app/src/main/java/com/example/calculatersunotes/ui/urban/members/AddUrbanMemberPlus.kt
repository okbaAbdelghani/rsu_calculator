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
import com.example.calculatersunotes.databinding.FragmentAddUrbanMemberPlusBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.utils.FragmentUtil

class AddUrbanMemberPlus : Fragment() {
    private var _binding : FragmentAddUrbanMemberPlusBinding? = null

    private lateinit var fragmentUtil: FragmentUtil
    private var exceptionButtonList: List<Button> = mutableListOf()
    private var buttonList: List<Button> = mutableListOf()
    private val urbanMemberViewModel: UrbanMemberViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUtil = FragmentUtil(requireContext())
        _binding = FragmentAddUrbanMemberPlusBinding.inflate(inflater, container, false)

        binding.backButton.setOnClickListener{
            navigateToAddMember()
        }

        buttonList = mutableListOf(
            binding.childBtn,
            binding.partnerBtn,
            binding.otherRelationshipBtn,
            binding.yesDiplomaBtn,
            binding.noDiplomaBtn,
            binding.hasJobBtn,
            binding.noJobBtn,
            binding.highPositionBtn,
            binding.machineryManagementActivityBtn,
            binding.otherBtn
        )

        exceptionButtonList = mutableListOf(
            binding.otherRelationshipBtn,
            binding.noDiplomaBtn,
            binding.noJobBtn,
            binding.otherBtn
        )

        val relationshipBtns = mutableListOf(
            binding.childBtn,
            binding.partnerBtn,
            binding.otherRelationshipBtn
        )

        val jobTypeButtons = mutableListOf(
            binding.highPositionBtn,
            binding.machineryManagementActivityBtn,
            binding.otherBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

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

        binding.highPositionBtn.setOnClickListener {
            urbanMemberViewModel.updatePositionStatus("high_position")
            fragmentUtil.setInactiveButtonColors(jobTypeButtons, binding.highPositionBtn)
        }

        binding.machineryManagementActivityBtn.setOnClickListener {
            urbanMemberViewModel.updatePositionStatus("machinery")
            fragmentUtil.setInactiveButtonColors(jobTypeButtons, binding.machineryManagementActivityBtn)
        }

        binding.otherBtn.setOnClickListener {
            urbanMemberViewModel.updatePositionStatus("other")
            fragmentUtil.setInactiveButtonColors(jobTypeButtons,  binding.otherBtn)
        }

        binding.noDiplomaBtn.setOnClickListener {
            urbanMemberViewModel.updateDiplomaPossession(false)
            fragmentUtil.toggleTwoOptions(binding.noDiplomaBtn, binding.yesDiplomaBtn)
        }

        binding.yesDiplomaBtn.setOnClickListener {
            urbanMemberViewModel.updateDiplomaPossession(true)
            fragmentUtil.toggleTwoOptions( binding.yesDiplomaBtn,  binding.noDiplomaBtn)
        }

        binding.hasJobBtn.setOnClickListener {
            urbanMemberViewModel.updateWorkStatus(true)
            fragmentUtil.toggleTwoOptions(binding.hasJobBtn, binding.noJobBtn)
        }

        binding.noJobBtn.setOnClickListener {
            urbanMemberViewModel.updateWorkStatus(false)
            fragmentUtil.toggleTwoOptions(binding.noJobBtn, binding.hasJobBtn)
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
            }
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }


        return binding.root
    }

    private fun navigateToAddMember() {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMember(), "left")
    }

}