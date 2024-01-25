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
import com.example.calculatersunotes.utils.FragmentUtil


class AddRuralMemberDetails : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralMemberViewModel: RuralMemberViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()

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

        noLiteracyBtn.setOnClickListener {
            ruralMemberViewModel.updateLiteracyStatus(false)
        }

        yesLiteracyBtn.setOnClickListener {
            ruralMemberViewModel.updateLiteracyStatus(true)
        }

        noHighEducationBtn.setOnClickListener {
            ruralMemberViewModel.updateHighEducationDiploma(false)
        }

        yesHighEducationBtn.setOnClickListener {
            ruralMemberViewModel.updateHighEducationDiploma(true)
        }

        yesCommerceBtn.setOnClickListener {
            ruralMemberViewModel.isMerchant(true)
        }

        noCommerceBtn.setOnClickListener {
            ruralMemberViewModel.isMerchant(false)
        }

        nextBtn.setOnClickListener {
            val familyMember = ruralMemberViewModel.ruralMember.value
            if (familyMember != null) {
                ruralFamilyViewModel.addFamilyMember(familyMember)
            }
        }

        return rootView
    }

}