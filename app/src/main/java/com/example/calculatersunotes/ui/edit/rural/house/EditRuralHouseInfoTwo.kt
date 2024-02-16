package com.example.calculatersunotes.ui.edit.rural.house

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentEditRuralHouseInfoTwoBinding
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException


class EditRuralHouseInfoTwo : Fragment() {
    private var _binding : FragmentEditRuralHouseInfoTwoBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private lateinit var editHousePagerAdapter: EditRuralHousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditRuralHouseInfoTwoBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = listOf(
            binding.noSecondHouseBtn,
            binding.yesSecondHouseBtn,
            binding.yesNonAgriculturalLandBtn,
            binding.noAgriculturalLandBtn,
            binding.noArableLandBtn,
            binding.yesArableLandBtn
        )

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val house = family.ruralHouse

            if(family.hasSecondHouse) {
                exceptionButtonList.add(binding.yesSecondHouseBtn)
            } else {
                exceptionButtonList.add(binding.noSecondHouseBtn)
            }

            if(house.hasNoAgriculturalLand) {
                exceptionButtonList.add(binding.yesNonAgriculturalLandBtn)
            } else {
                exceptionButtonList.add(binding.noAgriculturalLandBtn)
            }

            if(house.hasIrrigatedLand) {
                exceptionButtonList.add(binding.yesArableLandBtn)
            } else {
                exceptionButtonList.add(binding.noArableLandBtn)
            }

            binding.cowsNumberInput.setText(house.cowsNumber.toString())
            binding.roomsNumberInput.setText(house.roomsNumber.toString())

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        binding.noSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noSecondHouseBtn, binding.yesSecondHouseBtn)
            ruralFamilyViewModel.updateSecondHousePossession(false)
        }

        binding.yesSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesSecondHouseBtn, binding.noSecondHouseBtn)
            ruralFamilyViewModel.updateSecondHousePossession(true)
        }

        binding.yesNonAgriculturalLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesNonAgriculturalLandBtn, binding.noAgriculturalLandBtn)
            ruralHouseViewModel.updateNonAgriculturalLandPossession(true)
        }

        binding.noAgriculturalLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noAgriculturalLandBtn, binding.yesNonAgriculturalLandBtn)
            ruralHouseViewModel.updateNonAgriculturalLandPossession(false)
        }

        binding.yesArableLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesArableLandBtn, binding.noArableLandBtn)
            ruralHouseViewModel.updateIrrigatedLandPossession(true)
        }

        binding.noArableLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noArableLandBtn, binding.yesArableLandBtn)
            ruralHouseViewModel.updateIrrigatedLandPossession(false)
        }

        binding.cowsNumberInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0u

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toUInt()
                    } catch (e: ParseException) {
                        println(e.message)
                    }

                }
                ruralHouseViewModel.updateCowsNumber(number)
            }
        })

        binding.roomsNumberInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0u

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toUInt()
                    } catch (e: ParseException) {
                        println(e.message)
                    }
                }
                ruralHouseViewModel.updateRoomsNumber(number)
            }
        })

        swipeBack(binding.backButton)
        swipeNext(binding.includedNext.nextBtn)
        return binding.root
    }

    private fun swipeBack(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: EditRuralHouse = parentFragment as EditRuralHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHousePagerAdapter = EditRuralHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHousePagerAdapter)
        }
    }

    private fun swipeNext(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: EditRuralHouse = parentFragment as EditRuralHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHousePagerAdapter = EditRuralHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHousePagerAdapter) {}
        }
    }
}