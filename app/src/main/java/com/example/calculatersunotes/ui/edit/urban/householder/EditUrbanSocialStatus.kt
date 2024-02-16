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
import com.example.calculatersunotes.databinding.FragmentUrbanSocialStatusBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.urban.householder.UrbanHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class EditUrbanSocialStatus : Fragment() {
    private var _binding : FragmentUrbanSocialStatusBinding? = null

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
        _binding = FragmentUrbanSocialStatusBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val socialStatusButtons = listOf<Button>(
            binding.highPositionBtn,
            binding.mediumPositionBtn,
            binding.withoutStatusBtn,
        )

        val positionInJobButtons = listOf<Button>(
            binding.recruiterBtn,
            binding.employeeBtn,
            binding.withoutPositionBtn,
        )

        buttonList = mutableListOf(
            binding.highPositionBtn,
            binding.mediumPositionBtn,
            binding.withoutStatusBtn,
            binding.recruiterBtn,
            binding.employeeBtn,
            binding.withoutPositionBtn,
        )

        urbanFamilyViewModel.family.observe(viewLifecycleOwner) {
            val householder = it.householder

            if(householder.hasHighProfessionalPosition) {
                exceptionButtonList.add(binding.highPositionBtn)
            } else if (householder.hasAverageProfessionalPosition) {
                exceptionButtonList.add(binding.mediumPositionBtn)
            } else {
                exceptionButtonList.add(binding.withoutStatusBtn)
            }

            if(householder.hasAJob) {
                exceptionButtonList.add(binding.employeeBtn)
            } else if (householder.isRecruiting) {
                exceptionButtonList.add(binding.recruiterBtn)
            } else {
                exceptionButtonList.add(binding.withoutPositionBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        binding.highPositionBtn.setOnClickListener {
            setHouseHolderAsHighProfessionalPosition()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.highPositionBtn)
        }

        binding.mediumPositionBtn.setOnClickListener {
            setHouseHolderAsMediumPosition()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.mediumPositionBtn)
        }

        binding.withoutStatusBtn.setOnClickListener {
            setHouseHolderAsNone()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.withoutStatusBtn)
        }

        binding.recruiterBtn.setOnClickListener {
            setHouseHolderAsRecruiter()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, binding.recruiterBtn)
        }

        binding.employeeBtn.setOnClickListener {
            setHouseHolderAsEmployee()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, binding.employeeBtn)
        }

        binding.withoutPositionBtn.setOnClickListener {
            setHouseHolderAsWithoutPosition()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, binding.withoutPositionBtn)
        }

        binding.backButton.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseHolderAdapter)
        }

        binding.includedNext.nextBtn.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseHolderAdapter) {}
        }

        return binding.root
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