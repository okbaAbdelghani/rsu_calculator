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

class AddUrbanMemberMinus : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanMemberViewModel: UrbanMemberViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private var buttonList: List<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentUtil = FragmentUtil(requireContext())
        val rootView = inflater.inflate(R.layout.fragment_add_urban_member_minus, container, false)
        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)
        val doneBtn = rootView.findViewById<Button>(R.id.done_btn)

        val yesSchoolBtn = rootView.findViewById<Button>(R.id.yes_school_btn)
        val noSchoolBtn = rootView.findViewById<Button>(R.id.no_school_btn)
        val publicBtn = rootView.findViewById<Button>(R.id.public_btn)
        val privateBtn = rootView.findViewById<Button>(R.id.private_btn)


        buttonList = mutableListOf(
            noSchoolBtn,
            yesSchoolBtn,
            publicBtn,
            privateBtn
        )

        exceptionButtonList = mutableListOf(
            noSchoolBtn,
            privateBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        yesSchoolBtn.setOnClickListener {
            urbanMemberViewModel.updateSchooling(true)
            fragmentUtil.toggleTwoOptions(yesSchoolBtn, noSchoolBtn)
        }

        noSchoolBtn.setOnClickListener {
            urbanMemberViewModel.updateSchooling(false)
            fragmentUtil.toggleTwoOptions(noSchoolBtn, yesSchoolBtn)
        }

        publicBtn.setOnClickListener {
            urbanMemberViewModel.updateSchoolType("public")
            fragmentUtil.toggleTwoOptions(publicBtn, privateBtn)
        }

        privateBtn.setOnClickListener {
            urbanMemberViewModel.updateSchoolType("private")
            fragmentUtil.toggleTwoOptions(privateBtn, publicBtn)
        }

        backBtn.setOnClickListener{
            navigateToAddMember()
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
                println(member.fullName)
            }
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }
        return rootView
    }

    private fun navigateToAddMember() {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMember())
    }

}