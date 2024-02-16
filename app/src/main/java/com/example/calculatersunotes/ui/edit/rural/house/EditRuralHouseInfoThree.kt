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
import com.example.calculatersunotes.databinding.FragmentEditRuralHouseInfoThreeBinding
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException

class EditRuralHouseInfoThree : Fragment() {
    private var _binding : FragmentEditRuralHouseInfoThreeBinding? = null

    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private lateinit var editHousePagerAdapter: EditRuralHousePagerAdapter

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditRuralHouseInfoThreeBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val house = family.ruralHouse
            val bigBottles = (house.bigGasBottlesInMonth/40).toUInt().toString()
            val smallBottles = (house.smallGasBottlesInMonth/20).toUInt().toString()

            binding.bigGasBottles.setText(bigBottles)
            binding.smallGasBottles.setText(smallBottles)
            binding.internetAndPhoneBill.setText(house.internetAndPhoneExpensesInMonth.toString())

            binding.firstMonthElectricity.setText(house.electricityBillMonthOne.toString())
            binding.secondMonthElectricity.setText(house.electricityBillMonthTwo.toString())
            binding.thirdMonthElectricity.setText(house.electricityBillMonthThree.toString())

            binding.firstMonthWater.setText(house.waterBillMonthOne.toString())
            binding.secondMonthWater.setText(house.waterBillMonthTwo.toString())
            binding.thirdMonthWater.setText(house.waterBillMonthThree.toString())

        }

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
                ruralHouseViewModel.updateBigGasBottlesInMonth(number)

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
                ruralHouseViewModel.updateSmallGasBottlesInMonth(number)
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

                ruralHouseViewModel.updateInternetAndPhoneMonthlyBill(number)
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
                ruralHouseViewModel.updateElectricityMonthOne(number)
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
                ruralHouseViewModel.updateElectricityMonthTwo(number)
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
                ruralHouseViewModel.updateElectricityMonthThree(number)
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
                ruralHouseViewModel.updateWaterMonthFirst(number)
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
                ruralHouseViewModel.updateWaterMonthSecond(number)
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
                ruralHouseViewModel.updateWaterMonthThree(number)
            }
        })

        swipeBack(binding.backButton)

        binding.doneBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }

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

}