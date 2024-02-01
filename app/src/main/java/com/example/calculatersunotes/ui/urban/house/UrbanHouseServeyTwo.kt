package com.example.calculatersunotes.ui.urban.house

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
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.ui.rural.house.HousePagerAdapter
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException

class UrbanHouseServeyTwo : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseViewModel: UrbanHouseViewModel by activityViewModels()
    private lateinit var housePagerAdapter: UrbanHousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_urban_house_servey_two, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        val phonesNumberInput = rootView.findViewById<EditText>(R.id.phones_number_input)
        val motorcycleNumberInput = rootView.findViewById<EditText>(R.id.motorcycles_number_input)
        val carsNumberInput = rootView.findViewById<EditText>(R.id.cars_number_input)

        val yesGarageBtn = rootView.findViewById<Button>(R.id.yes_garage_btn)
        val noGarageBtn = rootView.findViewById<Button>(R.id.no_garage_btn)
        val noShopsBtn = rootView.findViewById<Button>(R.id.no_shops_btn)
        val yesShopsBtn = rootView.findViewById<Button>(R.id.yes_shops_btn)
        val yesComputerBtn = rootView.findViewById<Button>(R.id.yes_computer_btn)
        val noComputerBtn = rootView.findViewById<Button>(R.id.no_computer_btn)

        buttonList = mutableListOf(
            yesGarageBtn,
            noGarageBtn,
            noShopsBtn,
            yesShopsBtn,
            yesComputerBtn,
            noComputerBtn
        )

        exceptionButtonList = mutableListOf(
            noComputerBtn,
            noGarageBtn,
            noShopsBtn
        )

        yesGarageBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesGarageBtn , noGarageBtn)
            urbanHouseViewModel.updateGaragePossession(true)
        }

        noGarageBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noGarageBtn , yesGarageBtn)
            urbanHouseViewModel.updateGaragePossession(false)
        }

        yesShopsBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesShopsBtn , noShopsBtn)
            urbanHouseViewModel.updateShopsPossession(true)
        }

        noShopsBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noShopsBtn , yesShopsBtn)
            urbanHouseViewModel.updateShopsPossession(false)
        }

        yesComputerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesComputerBtn , noComputerBtn)
            urbanHouseViewModel.updateComputerPossession(true)
        }

        noComputerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noComputerBtn , yesComputerBtn)
            urbanHouseViewModel.updateComputerPossession(false)
        }

        phonesNumberInput.addTextChangedListener(object : TextWatcher {
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
                urbanHouseViewModel.updatePhonesNumber(number)
            }
        })

        motorcycleNumberInput.addTextChangedListener(object : TextWatcher {
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
                urbanHouseViewModel.updateMotorcycleNumber(number)
            }
        })

        carsNumberInput.addTextChangedListener(object : TextWatcher {
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
                urbanHouseViewModel.updateCarsNumber(number)
            }
        })

        swipeNext(rootView)
        swipeBack(backBtn)

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        return rootView
    }

    private fun swipeNext(rootView: View){
        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val parenFragment: UrbanHouseFragment = parentFragment as UrbanHouseFragment
        val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.urban_house_pager)
        housePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)

        nextBtn.setOnClickListener {
            swipeUtil.navigateNext(viewPager , housePagerAdapter) {}
        }
    }

    private fun swipeBack(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: UrbanHouseFragment = parentFragment as UrbanHouseFragment
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.urban_house_pager)

            housePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, housePagerAdapter)
        }
    }

}