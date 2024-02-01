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
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.congrats.HouseDoneCongrats
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException


class UrbanHouseServeyFour : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseViewModel: UrbanHouseViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private lateinit var housePagerAdapter: UrbanHousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_urban_house_servey_four, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        val bigGasBottles = rootView.findViewById<EditText>(R.id.big_gas_bottles)
        val smallGasBottles = rootView.findViewById<EditText>(R.id.small_gas_bottles)
        val internetAndPhoneBill = rootView.findViewById<EditText>(R.id.internet_and_phone_bill)

        val firstMonthElectricity = rootView.findViewById<EditText>(R.id.first_month_electricity)
        val secondMonthElectricity = rootView.findViewById<EditText>(R.id.second_month_electricity)
        val thirdMonthElectricity = rootView.findViewById<EditText>(R.id.third_month_electricity)

        val firstMonthWater = rootView.findViewById<EditText>(R.id.first_month_water)
        val secondMonthWater = rootView.findViewById<EditText>(R.id.second_month_water)
        val thirdMonthWater = rootView.findViewById<EditText>(R.id.third_month_water)



        bigGasBottles.addTextChangedListener(object : TextWatcher {
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

                urbanHouseViewModel.updateBigGasBottlesInMonth(number)
            }
        })

        smallGasBottles.addTextChangedListener(object : TextWatcher {
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
                urbanHouseViewModel.updateSmallGasBottlesInMonth(number)
            }
        })

        internetAndPhoneBill.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0.0
                if(enteredValue != "") {
                    try {
                        number = enteredValue.toDouble()
                    } catch (e: ParseException) {
                        println(e.message)
                    }
                }

                urbanHouseViewModel.updateInternetAndPhoneMonthlyBill(number)
            }
        })

        firstMonthElectricity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0.0

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toDouble()
                    } catch (e: ParseException) {
                        println(e.message)
                    }

                }
                urbanHouseViewModel.updateElectricityMonthOne(number)
            }
        })

        secondMonthElectricity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0.0

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toDouble()
                    } catch (e: ParseException) {
                        println(e.message)
                    }

                }
                urbanHouseViewModel.updateElectricityMonthTwo(number)
            }
        })

        thirdMonthElectricity.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0.0

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toDouble()
                    } catch (e: ParseException) {
                        println(e.message)
                    }

                }
                urbanHouseViewModel.updateElectricityMonthThree(number)
            }
        })

        firstMonthWater.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0.0

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toDouble()
                    } catch (e: ParseException) {
                        println(e.message)
                    }

                }
                urbanHouseViewModel.updateWaterMonthFirst(number)
            }
        })

        secondMonthWater.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0.0

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toDouble()
                    } catch (e: ParseException) {
                        println(e.message)
                    }

                }
                urbanHouseViewModel.updateWaterMonthSecond(number)
            }
        })

        thirdMonthWater.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0.0

                if(enteredValue != "") {
                    try {
                        number = enteredValue.toDouble()
                    } catch (e: ParseException) {
                        println(e.message)
                    }

                }
                urbanHouseViewModel.updateWaterMonthThree(number)
            }
        })

        swipeNext(rootView)
        swipeBack(backBtn)

        return rootView
    }


    private val navigateToResult : () -> Unit = {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, HouseDoneCongrats())
    }

    private fun swipeNext(rootView: View){
        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val parenFragment: UrbanHouseFragment = parentFragment as UrbanHouseFragment
        val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.urban_house_pager)
        housePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)

        nextBtn.setOnClickListener {
            swipeUtil.navigateNext(viewPager , housePagerAdapter , navigateToResult)
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