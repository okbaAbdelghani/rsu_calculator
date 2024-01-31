package com.example.calculatersunotes.ui.urban.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.utils.FragmentUtil

class UrbanSocialStatus : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_urban_social_status, container, false)
        fragmentUtil = FragmentUtil(requireContext())

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
            employeeBtn,
            recruiterBtn,
            withoutPositionBtn
        )

        buttonList = mutableListOf(
            highPositionBtn,
            mediumPositionBtn,
            withoutStatusBtn,
            employeeBtn,
            recruiterBtn,
            withoutPositionBtn
        )

        recruiterBtn.setOnClickListener {
            setHouseHolderAsRecruiter()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, recruiterBtn)
        }

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        return rootView
    }

    private fun setHouseHolderAsRecruiter(){
        urbanHouseHolderViewModel.updateJobPosition("employer")
    }

}