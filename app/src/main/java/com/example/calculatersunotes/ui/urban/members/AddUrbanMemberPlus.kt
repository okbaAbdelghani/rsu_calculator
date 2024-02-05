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
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.utils.FragmentUtil

class AddUrbanMemberPlus : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil
    private var exceptionButtonList: List<Button> = mutableListOf()
    private var buttonList: List<Button> = mutableListOf()
    private val urbanMemberViewModel: UrbanMemberViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUtil = FragmentUtil(requireContext())
        val rootView = inflater.inflate(R.layout.fragment_add_urban_member_plus, container, false)
        val doneBtn = rootView.findViewById<Button>(R.id.done_btn)
        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        backBtn.setOnClickListener{
            navigateToAddMember()
        }

        val childBtn = rootView.findViewById<Button>(R.id.child_btn)
        val partnerBtn = rootView.findViewById<Button>(R.id.partner_btn)
        val otherRelationshipBtn = rootView.findViewById<Button>(R.id.other_relationship_btn)
        val yesDiplomaBtn = rootView.findViewById<Button>(R.id.yes_diploma_btn)
        val noDiplomaBtn = rootView.findViewById<Button>(R.id.no_diploma_btn)
        val hasJobBtn = rootView.findViewById<Button>(R.id.has_job_btn)
        val noJobBtn = rootView.findViewById<Button>(R.id.no_job_btn)
        val highPositionBtn = rootView.findViewById<Button>(R.id.high_position_btn)
        val machineryManagementActivityBtn = rootView.findViewById<Button>(R.id.machinery_management_activity_btn)
        val otherJobBtn = rootView.findViewById<Button>(R.id.other_btn)

        buttonList = mutableListOf(
            childBtn,
            partnerBtn,
            otherRelationshipBtn,
            yesDiplomaBtn,
            noDiplomaBtn,
            hasJobBtn,
            noJobBtn,
            highPositionBtn,
            machineryManagementActivityBtn,
            otherJobBtn
        )

        exceptionButtonList = mutableListOf(
            otherRelationshipBtn,
            noDiplomaBtn,
            noJobBtn,
            otherJobBtn
        )

        val relationshipBtns = mutableListOf(
            childBtn,
            partnerBtn,
            otherRelationshipBtn
        )

        val jobTypeButtons = mutableListOf(
            highPositionBtn,
            machineryManagementActivityBtn,
            otherJobBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        childBtn.setOnClickListener {
            urbanMemberViewModel.updateRelationship("child")
            fragmentUtil.setInactiveButtonColors(relationshipBtns, childBtn)
        }

        partnerBtn.setOnClickListener {
            urbanMemberViewModel.updateRelationship("partner")
            fragmentUtil.setInactiveButtonColors(relationshipBtns, partnerBtn)
        }

        otherRelationshipBtn.setOnClickListener {
            urbanMemberViewModel.updateRelationship("other")
            fragmentUtil.setInactiveButtonColors(relationshipBtns, otherRelationshipBtn)
        }

        highPositionBtn.setOnClickListener {
            urbanMemberViewModel.updatePositionStatus("high_position")
            fragmentUtil.setInactiveButtonColors(jobTypeButtons, highPositionBtn)
        }

        machineryManagementActivityBtn.setOnClickListener {
            urbanMemberViewModel.updatePositionStatus("machinery")
            fragmentUtil.setInactiveButtonColors(jobTypeButtons, machineryManagementActivityBtn)
        }

        otherJobBtn.setOnClickListener {
            urbanMemberViewModel.updatePositionStatus("other")
            fragmentUtil.setInactiveButtonColors(jobTypeButtons, otherJobBtn)
        }

        noDiplomaBtn.setOnClickListener {
            urbanMemberViewModel.updateDiplomaPossession(false)
            fragmentUtil.toggleTwoOptions(noDiplomaBtn, yesDiplomaBtn)
        }

        yesDiplomaBtn.setOnClickListener {
            urbanMemberViewModel.updateDiplomaPossession(true)
            fragmentUtil.toggleTwoOptions(yesDiplomaBtn, noDiplomaBtn)
        }

        hasJobBtn.setOnClickListener {
            urbanMemberViewModel.updateWorkStatus(true)
            fragmentUtil.toggleTwoOptions(hasJobBtn, noJobBtn)
        }

        noJobBtn.setOnClickListener {
            urbanMemberViewModel.updateWorkStatus(false)
            fragmentUtil.toggleTwoOptions(noJobBtn, hasJobBtn)
        }

        doneBtn.setOnClickListener {
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


        return rootView
    }

    private fun navigateToAddMember() {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMember())
    }

}