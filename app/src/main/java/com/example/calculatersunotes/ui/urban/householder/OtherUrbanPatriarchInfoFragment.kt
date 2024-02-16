package com.example.calculatersunotes.ui.urban.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentOtherUrbanPatriarchInfoBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.congrats.HouseHolderDoneCongrats
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class OtherUrbanPatriarchInfoFragment : Fragment() {
    private var _binding : FragmentOtherUrbanPatriarchInfoBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private val urbanHouseViewModel: UrbanFamilyViewModel by activityViewModels()
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

        _binding = FragmentOtherUrbanPatriarchInfoBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = mutableListOf(
            binding.isRetiredOrBeneficiaryOfIncomeBtn,
            binding.withoutIncomeBtn,
            binding.hasHealthSecurityBtn,
            binding.withoutHealthSecurityBtn,
            binding.hasDiplomaBtn,
            binding.withoutDiplomaBtn,
            binding.machineryManagementYesBtn,
            binding.machineryManagementNoBtn
        )

        exceptionButtonList = mutableListOf(
            binding.withoutIncomeBtn,
            binding.withoutDiplomaBtn,
            binding.machineryManagementNoBtn,
            binding.withoutHealthSecurityBtn
        )

        binding.isRetiredOrBeneficiaryOfIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(true)
            fragmentUtil.toggleTwoOptions(binding.isRetiredOrBeneficiaryOfIncomeBtn, binding.withoutIncomeBtn)
        }

        binding.withoutIncomeBtn.setOnClickListener {
            urbanHouseHolderViewModel.retirementPossession(false)
            fragmentUtil.toggleTwoOptions(binding.withoutIncomeBtn, binding.isRetiredOrBeneficiaryOfIncomeBtn)
        }

        binding.hasDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(true)
            fragmentUtil.toggleTwoOptions(binding.hasDiplomaBtn, binding.withoutDiplomaBtn)
        }

        binding.withoutDiplomaBtn.setOnClickListener {
            urbanHouseHolderViewModel.diplomaPossession(false)
            fragmentUtil.toggleTwoOptions(binding.withoutDiplomaBtn, binding.hasDiplomaBtn)
        }

        binding.hasHealthSecurityBtn.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(true)
            fragmentUtil.toggleTwoOptions(binding.hasHealthSecurityBtn, binding.withoutHealthSecurityBtn)
        }

        binding.withoutHealthSecurityBtn.setOnClickListener {
            urbanHouseHolderViewModel.healthSecurityPossession(false)
            fragmentUtil.toggleTwoOptions(binding.withoutHealthSecurityBtn, binding.hasHealthSecurityBtn)
        }

        binding.machineryManagementYesBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(true)
            fragmentUtil.toggleTwoOptions(binding.machineryManagementYesBtn, binding.machineryManagementNoBtn)
        }

        binding.machineryManagementNoBtn.setOnClickListener {
            urbanHouseHolderViewModel.machineryActivityManagement(false)
            fragmentUtil.toggleTwoOptions(binding.machineryManagementNoBtn, binding.machineryManagementYesBtn)
        }

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        swipeBack()
        onNexBtnClicked()

        return binding.root
    }

    private fun swipeBack() {
        binding.backButton.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, urbanPagerAdapter)
        }
    }

    private fun onNexBtnClicked() {
        binding.includedNext.nextBtn.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, urbanPagerAdapter, navigateToHouseHolderCongrats)
            if (viewPager != null) {
                if(viewPager.currentItem == urbanPagerAdapter.count - 1) {
                    setFamilyHouseHolder()
                }
            }
        }
    }

    private val navigateToHouseHolderCongrats : () -> Unit = {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, HouseHolderDoneCongrats())
    }

    private fun setFamilyHouseHolder() {
        urbanHouseHolderViewModel.urbanHouseHolder.observe(viewLifecycleOwner) { houseHolder ->
            urbanHouseViewModel.updateFamilyHouseHolder(houseHolder)
        }
    }

}