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
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException

class EditRuralHouseInfoThree : Fragment() {
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private lateinit var editHousePagerAdapter: EditRuralHousePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_rural_house_info_three, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)
        val doneBtn = rootView.findViewById<Button>(R.id.done_btn)

        val bigGasBottles = rootView.findViewById<EditText>(R.id.big_gas_bottles)
        val smallGasBottles = rootView.findViewById<EditText>(R.id.small_gas_bottles)
        val internetAndPhoneBill = rootView.findViewById<EditText>(R.id.internet_and_phone_bill)

        val firstMonthElectricity = rootView.findViewById<EditText>(R.id.first_month_electricity)
        val secondMonthElectricity = rootView.findViewById<EditText>(R.id.second_month_electricity)
        val thirdMonthElectricity = rootView.findViewById<EditText>(R.id.third_month_electricity)

        val firstMonthWater = rootView.findViewById<EditText>(R.id.first_month_water)
        val secondMonthWater = rootView.findViewById<EditText>(R.id.second_month_water)
        val thirdMonthWater = rootView.findViewById<EditText>(R.id.third_month_water)

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val house = family.ruralHouse
            val bigBottles = (house.bigGasBottlesInMonth/40).toUInt().toString()
            val smallBottles = (house.smallGasBottlesInMonth/20).toUInt().toString()

            bigGasBottles.setText(bigBottles)
            smallGasBottles.setText(smallBottles)
            internetAndPhoneBill.setText(house.internetAndPhoneExpensesInMonth.toString())

            firstMonthElectricity.setText(house.electricityBillMonthOne.toString())
            secondMonthElectricity.setText(house.electricityBillMonthTwo.toString())
            thirdMonthElectricity.setText(house.electricityBillMonthThree.toString())

            firstMonthWater.setText(house.waterBillMonthOne.toString())
            secondMonthWater.setText(house.waterBillMonthTwo.toString())
            thirdMonthWater.setText(house.waterBillMonthThree.toString())

        }

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
                ruralHouseViewModel.updateBigGasBottlesInMonth(number)

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
                ruralHouseViewModel.updateSmallGasBottlesInMonth(number)
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

                ruralHouseViewModel.updateInternetAndPhoneMonthlyBill(number)
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
                ruralHouseViewModel.updateElectricityMonthOne(number)
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
                ruralHouseViewModel.updateElectricityMonthTwo(number)
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
                ruralHouseViewModel.updateElectricityMonthThree(number)
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
                ruralHouseViewModel.updateWaterMonthFirst(number)
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
                ruralHouseViewModel.updateWaterMonthSecond(number)
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
                ruralHouseViewModel.updateWaterMonthThree(number)
            }
        })

        swipeBack(backBtn)

        doneBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }

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

}