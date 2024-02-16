package com.example.calculatersunotes.ui.edit.rural.house

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
import com.example.calculatersunotes.databinding.FragmentEditRuralHouseInfoOneBinding
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class EditRuralHouseInfoOne : Fragment() {
    private var _binding : FragmentEditRuralHouseInfoOneBinding? = null

    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    private lateinit var editHouseAdapter: EditRuralHousePagerAdapter

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditRuralHouseInfoOneBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = mutableListOf(
            binding.noHouseTypeBtn,
            binding.yesHouseTypeBtn,
            binding.noCarBtn,
            binding.yesCarBtn,
            binding.noMotorcycleBtn,
            binding.yesMotorcycleBtn
        )

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) {
            var house = it.ruralHouse

            if(house.isHouseTypeOne) {
                exceptionButtonList.add(binding.noHouseTypeBtn)
            } else {
                exceptionButtonList.add(binding.yesHouseTypeBtn)
            }

            if(house.hasCar) {
                exceptionButtonList.add(binding.yesCarBtn)
            } else {
                exceptionButtonList.add(binding.noCarBtn)
            }

            if(house.hasMotorcycle) {
                exceptionButtonList.add(binding.yesMotorcycleBtn)
            } else {
                exceptionButtonList.add(binding.noMotorcycleBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        binding.noHouseTypeBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noHouseTypeBtn, binding.yesHouseTypeBtn)
            ruralHouseViewModel.updateHouseType(false)
        }

        binding.yesHouseTypeBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesHouseTypeBtn , binding.noHouseTypeBtn)
            ruralHouseViewModel.updateHouseType(true)
        }

        binding.noCarBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noCarBtn , binding.yesCarBtn)
            ruralHouseViewModel.updateCarPossession(false)
        }

        binding.yesCarBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesHouseTypeBtn , binding.noCarBtn)
            ruralHouseViewModel.updateCarPossession(true)
        }

        binding.noMotorcycleBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noMotorcycleBtn , binding.yesMotorcycleBtn)
            ruralHouseViewModel.updateCarPossession(false)
        }

        binding.yesMotorcycleBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesMotorcycleBtn , binding.noMotorcycleBtn)
            ruralHouseViewModel.updateCarPossession(true)
        }

        binding.backButton.setOnClickListener {
            val parenFragment: EditRuralHouse = parentFragment as EditRuralHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHouseAdapter = EditRuralHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseAdapter)
        }

        binding.nextInclude.nextBtn.setOnClickListener {
            val parenFragment: EditRuralHouse = parentFragment as EditRuralHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHouseAdapter = EditRuralHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseAdapter) {}
        }


        return binding.root
    }

}