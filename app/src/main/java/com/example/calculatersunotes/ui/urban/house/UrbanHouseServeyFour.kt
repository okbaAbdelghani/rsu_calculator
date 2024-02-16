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
import com.example.calculatersunotes.databinding.FragmentUrbanHouseServeyFourBinding
import com.example.calculatersunotes.databinding.FragmentUrbanHouseServeyThreeBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.congrats.HouseDoneCongrats
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException


class UrbanHouseServeyFour : Fragment() {
    private var _binding: FragmentUrbanHouseServeyFourBinding? = null

    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseViewModel: UrbanHouseViewModel by activityViewModels()
    private lateinit var housePagerAdapter: UrbanHousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUrbanHouseServeyFourBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        binding.bigGasBottles.addTextChangedListener(object : TextWatcher {
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

        binding.smallGasBottles.addTextChangedListener(object : TextWatcher {
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

        binding.internetAndPhoneBill.addTextChangedListener(object : TextWatcher {
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

        binding.firstMonthElectricity.addTextChangedListener(object : TextWatcher {
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

        binding.secondMonthElectricity.addTextChangedListener(object : TextWatcher {
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

        binding.thirdMonthElectricity.addTextChangedListener(object : TextWatcher {
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

        binding.firstMonthWater.addTextChangedListener(object : TextWatcher {
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

        binding.secondMonthWater.addTextChangedListener(object : TextWatcher {
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

        binding.thirdMonthWater.addTextChangedListener(object : TextWatcher {
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

        swipeNext()
        swipeBack()

        return binding.root
    }


    private val navigateToResult : () -> Unit = {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, HouseDoneCongrats())
    }

    private fun swipeNext(){

        val parenFragment: UrbanHouseFragment = parentFragment as UrbanHouseFragment
        val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.urban_house_pager)
        housePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)

        binding.includedNext.nextBtn.setOnClickListener {
            swipeUtil.navigateNext(viewPager , housePagerAdapter , navigateToResult)
        }
    }


    private fun swipeBack() {
        binding.backButton.setOnClickListener {
            val parenFragment: UrbanHouseFragment = parentFragment as UrbanHouseFragment
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.urban_house_pager)

            housePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, housePagerAdapter)
        }
    }

}