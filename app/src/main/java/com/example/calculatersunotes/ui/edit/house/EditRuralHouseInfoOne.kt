package com.example.calculatersunotes.ui.edit.house

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
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class EditRuralHouseInfoOne : Fragment() {
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: MutableList<Button> = mutableListOf()
    private lateinit var editHouseAdapter: EditHousePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_rural_house_info_one, container, false)

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)
        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val noHouseTypeBtn = rootView.findViewById<Button>(R.id.no_house_type_btn)
        val yesHouseTypeBtn = rootView.findViewById<Button>(R.id.yes_house_type_btn)
        val noCarBtn = rootView.findViewById<Button>(R.id.no_car_btn)
        val yesCarBtn = rootView.findViewById<Button>(R.id.yes_car_btn)
        val noMotorcycleBtn = rootView.findViewById<Button>(R.id.no_motorcycle_btn)
        val yesMotorcycleBtn = rootView.findViewById<Button>(R.id.yes_motorcycle_btn)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = mutableListOf(
            noHouseTypeBtn,
            yesHouseTypeBtn,
            noCarBtn,
            yesCarBtn,
            noMotorcycleBtn,
            yesMotorcycleBtn
        )

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            var house = family.ruralHouse

            if(house.isHouseTypeOne) {
                exceptionButtonList.add(yesHouseTypeBtn)
            } else {
                exceptionButtonList.add(noHouseTypeBtn)
            }

            if(house.hasCar) {
                exceptionButtonList.add(yesCarBtn)
            } else {
                exceptionButtonList.add(noCarBtn)
            }

            if(house.hasMotorcycle) {
                exceptionButtonList.add(yesMotorcycleBtn)
            } else {
                exceptionButtonList.add(noMotorcycleBtn)
            }

            fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)
        }

        noHouseTypeBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noHouseTypeBtn, yesHouseTypeBtn)
            ruralHouseViewModel.updateHouseType(false)
        }

        yesHouseTypeBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesHouseTypeBtn , noHouseTypeBtn)
            ruralHouseViewModel.updateHouseType(true)
        }

        noCarBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noCarBtn , yesCarBtn)
            ruralHouseViewModel.updateCarPossession(false)
        }

        yesCarBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesCarBtn , noCarBtn)
            ruralHouseViewModel.updateCarPossession(true)
        }

        noMotorcycleBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noMotorcycleBtn , yesMotorcycleBtn)
            ruralHouseViewModel.updateCarPossession(false)
        }

        yesMotorcycleBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesMotorcycleBtn , noMotorcycleBtn)
            ruralHouseViewModel.updateCarPossession(true)
        }

        backBtn.setOnClickListener {
            val parenFragment: EditHouse = parentFragment as EditHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHouseAdapter = EditHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHouseAdapter)
        }

        nextBtn.setOnClickListener {
            val parenFragment: EditHouse = parentFragment as EditHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHouseAdapter = EditHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseAdapter) {}
        }


        return rootView
    }

}