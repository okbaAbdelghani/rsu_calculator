package com.example.calculatersunotes.ui.edit.rural.house

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException


class EditRuralHouseInfoTwo : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private lateinit var editHousePagerAdapter: EditRuralHousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_edit_rural_house_info_two, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)
        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val noSecondHouseBtn = rootView.findViewById<Button>(R.id.no_second_house_btn)
        val yesSecondHouseBtn = rootView.findViewById<Button>(R.id.yes_second_house_btn)
        val yesNonAgriculturalLandBtn = rootView.findViewById<Button>(R.id.yes_non_agricultural_land_btn)
        val noAgriculturalLandBtn = rootView.findViewById<Button>(R.id.no_agricultural_land_btn)
        val noArableLandBtn = rootView.findViewById<Button>(R.id.no_arable_land_btn)
        val yesArableLandBtn = rootView.findViewById<Button>(R.id.yes_arable_land_btn)

        val cowsNumberInput = rootView.findViewById<EditText>(R.id.cows_number_input)
        val roomsNumberInput = rootView.findViewById<EditText>(R.id.rooms_number_input)

        buttonList = listOf(
            noSecondHouseBtn,
            yesSecondHouseBtn,
            yesNonAgriculturalLandBtn,
            noAgriculturalLandBtn,
            noArableLandBtn,
            yesArableLandBtn
        )

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val house = family.ruralHouse

            if(family.hasSecondHouse) {
                exceptionButtonList.add(yesSecondHouseBtn)
            } else {
                exceptionButtonList.add(noSecondHouseBtn)
            }

            if(house.hasNoAgriculturalLand) {
                exceptionButtonList.add(yesNonAgriculturalLandBtn)
            } else {
                exceptionButtonList.add(noAgriculturalLandBtn)
            }

            if(house.hasIrrigatedLand) {
                exceptionButtonList.add(yesArableLandBtn)
            } else {
                exceptionButtonList.add(noArableLandBtn)
            }

            cowsNumberInput.setText(house.cowsNumber.toString())
            roomsNumberInput.setText(house.roomsNumber.toString())

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        noSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noSecondHouseBtn, yesSecondHouseBtn)
            ruralFamilyViewModel.updateSecondHousePossession(false)
        }

        yesSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesSecondHouseBtn, noSecondHouseBtn)
            ruralFamilyViewModel.updateSecondHousePossession(true)
        }

        yesNonAgriculturalLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesNonAgriculturalLandBtn, noAgriculturalLandBtn)
            ruralHouseViewModel.updateNonAgriculturalLandPossession(true)
        }

        noAgriculturalLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noAgriculturalLandBtn, yesNonAgriculturalLandBtn)
            ruralHouseViewModel.updateNonAgriculturalLandPossession(false)
        }

        yesArableLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesArableLandBtn, noArableLandBtn)
            ruralHouseViewModel.updateIrrigatedLandPossession(true)
        }

        noArableLandBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noArableLandBtn, yesArableLandBtn)
            ruralHouseViewModel.updateIrrigatedLandPossession(false)
        }

        cowsNumberInput.addTextChangedListener(object : TextWatcher {
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

        roomsNumberInput.addTextChangedListener(object : TextWatcher {
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

        swipeBack(backBtn)
        swipeNext(nextBtn)
        return rootView
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