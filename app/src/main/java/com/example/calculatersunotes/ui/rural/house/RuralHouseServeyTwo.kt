package com.example.calculatersunotes.ui.rural.house

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
import com.example.calculatersunotes.ui.congrats.HouseDoneCongrats
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class RuralHouseServeyTwo : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private lateinit var housePagerAdapter: HousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_rural_house_servey_two, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

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

        exceptionButtonList = listOf(
            noSecondHouseBtn,
            noArableLandBtn,
            noAgriculturalLandBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

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
                val number = enteredValue.toUInt()
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
                val number = enteredValue.toUInt()
                ruralHouseViewModel.updateRoomsNumber(number)
            }
        })


        swipeNext(rootView)
        return rootView
    }

    private fun swipeNext(rootView: View){
        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val parenFragment: RuralHouseFragment = parentFragment as RuralHouseFragment
        val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.rural_house_pager)
        housePagerAdapter = HousePagerAdapter(childFragmentManager)

        //Swipe Next
        nextBtn.setOnClickListener {
            swipeUtil.navigateNext(viewPager , housePagerAdapter, navigateToHouseCongrats)
        }
    }

    private val navigateToHouseCongrats : () -> Unit = {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, HouseDoneCongrats())
    }

}