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
import com.example.calculatersunotes.databinding.FragmentUrbanSocialStatusBinding
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class UrbanSocialStatus : Fragment() {
    private var _binding : FragmentUrbanSocialStatusBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var urbanPagerAdapter: UrbanPagerAdapter
    private lateinit var swipeUtil: SwipeUtil

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
            binding.employeeBtn,
            binding.recruiterBtn,
            binding.withoutPositionBtn
        )

        buttonList = mutableListOf(
            binding.highPositionBtn,
            binding.mediumPositionBtn,
            binding.withoutStatusBtn,
            binding.employeeBtn,
            binding.recruiterBtn,
            binding.withoutPositionBtn
        )

        exceptionButtonList = mutableListOf(
            binding.withoutPositionBtn,
            binding.withoutStatusBtn
        )

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

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        swipeBack(binding.backButton)
        swipeNext(binding.includedNext.nextBtn)


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