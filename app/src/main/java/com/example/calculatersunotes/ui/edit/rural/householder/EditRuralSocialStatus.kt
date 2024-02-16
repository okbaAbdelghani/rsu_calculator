package com.example.calculatersunotes.ui.edit.rural.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentEditSocialStatusBinding
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class EditRuralSocialStatus : Fragment() {
    private var _binding : FragmentEditSocialStatusBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var editHouseHolderAdapter: EditRuralHouseholderAdapter

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditSocialStatusBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val socialStatusButtons = listOf<Button>(
            binding.machineryManagerBtn,
            binding.highPositionBtn,
            binding.craftsmanBtn,
            binding.farmerBtn,
            binding.otherBtn
        )

        val positionInJobButtons = listOf<Button>(
            binding.recruiterBtn,
            binding.withoutBtn,
        )

        buttonList = mutableListOf(
            binding.machineryManagerBtn,
            binding.craftsmanBtn,
            binding.farmerBtn,
            binding.otherBtn,
            binding.recruiterBtn,
            binding.highPositionBtn,
            binding.withoutBtn
        )

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) {

            val householder = it.householder

            if(householder.isRecruiting){
                exceptionButtonList.add(binding.recruiterBtn)
            } else {
                exceptionButtonList.add(binding.withoutBtn)
            }

            if(householder.fixEquipmentOrMachinery) {
                exceptionButtonList.add(binding.machineryManagerBtn)
            }

            else if(householder.hasHighProfessionalPosition) {
                exceptionButtonList.add(binding.highPositionBtn)
            }

            else if(householder.isCraftsman) {
                exceptionButtonList.add(binding.craftsmanBtn)
            }

            else if(householder.isFarmer) {
                exceptionButtonList.add(binding.farmerBtn)
            }

            else {
                exceptionButtonList.add(binding.otherBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        }

        binding.machineryManagerBtn.setOnClickListener {
            setHouseHolderAsMachineryManager()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.machineryManagerBtn)
        }

        binding.craftsmanBtn.setOnClickListener {
            setHouseHolderAsCraftsman()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.craftsmanBtn)
        }

        binding.farmerBtn.setOnClickListener {
            setHouseHolderAsFarmer()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.farmerBtn)
        }

        binding.highPositionBtn.setOnClickListener {
            setHouseHolderAsHighProfessionalPosition()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.highPositionBtn)
        }

        binding.otherBtn.setOnClickListener {
            setHouseHolderAsNone()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, binding.otherBtn)
        }

        binding.recruiterBtn.setOnClickListener {
            setHouseHolderAsRecruiter()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, binding.recruiterBtn)
        }

        binding.withoutBtn.setOnClickListener {
            setHouseHolderAsJobless()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, binding.withoutBtn)
        }

        binding.backButton.setOnClickListener {
            val parenFragment: EditRuralHouseholder = parentFragment as EditRuralHouseholder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_householder_pager)

            editHouseHolderAdapter = EditRuralHouseholderAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseHolderAdapter)
        }

        binding.includedNext.nextBtn.setOnClickListener {
            val parenFragment: EditRuralHouseholder = parentFragment as EditRuralHouseholder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_householder_pager)

            editHouseHolderAdapter = EditRuralHouseholderAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseHolderAdapter) {}
        }

        return binding.root
    }

    private fun setHouseHolderAsFarmer(){
        ruralHouseHolderViewModel.updateSocialStatus("farmer")
    }
    private fun setHouseHolderAsCraftsman(){
        ruralHouseHolderViewModel.updateSocialStatus("craftsman")
    }
    private fun setHouseHolderAsMachineryManager(){
        ruralHouseHolderViewModel.updateSocialStatus("machinery_manager")
    }
    private fun setHouseHolderAsHighProfessionalPosition(){
        ruralHouseHolderViewModel.updateSocialStatus("high_position")
    }
    private fun setHouseHolderAsNone(){
        ruralHouseHolderViewModel.updateSocialStatus("none")
    }
    private fun setHouseHolderAsRecruiter(){
        ruralHouseHolderViewModel.updateJobPosition("employer")
    }
    private fun setHouseHolderAsJobless(){
        ruralHouseHolderViewModel.updateJobPosition("without")
    }



}