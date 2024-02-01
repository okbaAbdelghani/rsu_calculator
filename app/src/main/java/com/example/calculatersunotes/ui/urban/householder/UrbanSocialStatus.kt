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
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class UrbanSocialStatus : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var urbanPagerAdapter: UrbanPagerAdapter
    private lateinit var swipeUtil: SwipeUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_urban_social_status, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)
        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

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

        exceptionButtonList = mutableListOf(
            withoutPositionBtn,
            withoutStatusBtn
        )

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

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        swipeBack(backBtn)
        swipeNext(nextBtn)


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

    private fun swipeBack(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, urbanPagerAdapter)
        }
    }

    private fun swipeNext(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, urbanPagerAdapter) {}
        }
    }

}