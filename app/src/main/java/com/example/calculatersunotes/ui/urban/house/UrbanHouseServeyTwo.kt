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
import com.example.calculatersunotes.databinding.FragmentUrbanHouseServeyOneBinding
import com.example.calculatersunotes.databinding.FragmentUrbanHouseServeyTwoBinding
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.ui.rural.house.HousePagerAdapter
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.text.ParseException

class UrbanHouseServeyTwo : Fragment() {
    private var _binding: FragmentUrbanHouseServeyTwoBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
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
        _binding = FragmentUrbanHouseServeyTwoBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = mutableListOf(
            binding.yesGarageBtn,
            binding.noGarageBtn,
            binding.noShopsBtn,
            binding.yesShopsBtn,
            binding.yesComputerBtn,
            binding.noComputerBtn
        )

        exceptionButtonList = mutableListOf(
            binding.noComputerBtn,
            binding.noGarageBtn,
            binding.noShopsBtn
        )

        binding.yesGarageBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesGarageBtn , binding.noGarageBtn)
            urbanHouseViewModel.updateGaragePossession(true)
        }

        binding.noGarageBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noGarageBtn , binding.yesGarageBtn)
            urbanHouseViewModel.updateGaragePossession(false)
        }

        binding.yesShopsBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesShopsBtn , binding.noShopsBtn)
            urbanHouseViewModel.updateShopsPossession(true)
        }

        binding.noShopsBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noShopsBtn , binding.yesShopsBtn)
            urbanHouseViewModel.updateShopsPossession(false)
        }

        binding.yesComputerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesComputerBtn , binding.noComputerBtn)
            urbanHouseViewModel.updateComputerPossession(true)
        }

        binding.noComputerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noComputerBtn , binding.yesComputerBtn)
            urbanHouseViewModel.updateComputerPossession(false)
        }

        binding.phonesNumberInput.addTextChangedListener(object : TextWatcher {
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

        binding.motorcyclesNumberInput.addTextChangedListener(object : TextWatcher {
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

        binding.carsNumberInput.addTextChangedListener(object : TextWatcher {
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

        swipeNext()
        swipeBack()

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        return binding.root
    }

    private fun swipeNext(){
        val parenFragment: UrbanHouseFragment = parentFragment as UrbanHouseFragment
        val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.urban_house_pager)
        housePagerAdapter = UrbanHousePagerAdapter(childFragmentManager)

        binding.includedNext.nextBtn.setOnClickListener {
            swipeUtil.navigateNext(viewPager , housePagerAdapter) {}
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