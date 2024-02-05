package com.example.calculatersunotes.ui.edit.urban.householder

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
import com.example.calculatersunotes.ui.edit.rural.householder.EditRuralHouseholder
import com.example.calculatersunotes.ui.edit.rural.householder.EditRuralHouseholderAdapter
import com.example.calculatersunotes.ui.urban.householder.UrbanHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class EditUrbanSocialStatus : Fragment() {
    private var buttonList: MutableList<Button> = mutableListOf()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var editHouseHolderAdapter: EditUrbanHouseHolderAdapter
    private var exceptionButtonList: MutableList<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_urban_social_status, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)
        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val highPositionBtn = rootView.findViewById<Button>(R.id.high_position_btn)
        val mediumPositionBtn = rootView.findViewById<Button>(R.id.medium_position_btn)
        val withoutStatusBtn = rootView.findViewById<Button>(R.id.without_status_btn)
        val employeeBtn = rootView.findViewById<Button>(R.id.employee_btn)
        val recruiterBtn = rootView.findViewById<Button>(R.id.recruiter_btn)
        val withoutPositionBtn = rootView.findViewById<Button>(R.id.without_position_btn)

        val socialStatusButtons = listOf<Button>(
            highPositionBtn,
            mediumPositionBtn,
            withoutStatusBtn,
        )

        val positionInJobButtons = listOf<Button>(
            recruiterBtn,
            employeeBtn,
            withoutPositionBtn,
        )

        buttonList = mutableListOf(
            highPositionBtn,
            mediumPositionBtn,
            withoutStatusBtn,
            recruiterBtn,
            employeeBtn,
            withoutPositionBtn,
        )

        urbanFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val householder = family.householder

            if(householder.hasHighProfessionalPosition) {
                exceptionButtonList.add(highPositionBtn)
            } else if (householder.hasAverageProfessionalPosition) {
                exceptionButtonList.add(mediumPositionBtn)
            } else {
                exceptionButtonList.add(withoutStatusBtn)
            }

            if(householder.hasAJob) {
                exceptionButtonList.add(employeeBtn)
            } else if (householder.isRecruiting) {
                exceptionButtonList.add(recruiterBtn)
            } else {
                exceptionButtonList.add(withoutPositionBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        highPositionBtn.setOnClickListener {
            setHouseHolderAsHighProfessionalPosition()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, highPositionBtn)
        }

        mediumPositionBtn.setOnClickListener {
            setHouseHolderAsMediumPosition()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, mediumPositionBtn)
        }

        withoutStatusBtn.setOnClickListener {
            setHouseHolderAsNone()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, withoutStatusBtn)
        }

        recruiterBtn.setOnClickListener {
            setHouseHolderAsRecruiter()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, recruiterBtn)
        }

        employeeBtn.setOnClickListener {
            setHouseHolderAsEmployee()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, employeeBtn)
        }

        withoutPositionBtn.setOnClickListener {
            setHouseHolderAsWithoutPosition()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, withoutPositionBtn)
        }

        backBtn.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseHolderAdapter)
        }

        nextBtn.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseHolderAdapter) {}
        }

        return rootView
    }

    private fun setHouseHolderAsEmployee() {
        urbanHouseHolderViewModel.updateJobPosition("employee")
    }

    private fun setHouseHolderAsRecruiter(){
        urbanHouseHolderViewModel.updateJobPosition("employer")
    }

    private fun setHouseHolderAsHighProfessionalPosition(){
        urbanHouseHolderViewModel.updateSocialStatus("high_position")
    }

    private fun setHouseHolderAsWithoutPosition(){
        urbanHouseHolderViewModel.updateSocialStatus("without")
    }

    private fun setHouseHolderAsMediumPosition(){
        urbanHouseHolderViewModel.updateSocialStatus("medium_position")
    }

    private fun setHouseHolderAsNone(){
        urbanHouseHolderViewModel.updateSocialStatus("none")
    }

}