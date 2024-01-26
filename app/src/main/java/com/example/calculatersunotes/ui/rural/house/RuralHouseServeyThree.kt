package com.example.calculatersunotes.ui.rural.house

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.congrats.HouseDoneCongrats
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException
import java.util.InputMismatchException

class RuralHouseServeyThree : Fragment() {
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private lateinit var housePagerAdapter: HousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_rural_house_servey_three, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

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