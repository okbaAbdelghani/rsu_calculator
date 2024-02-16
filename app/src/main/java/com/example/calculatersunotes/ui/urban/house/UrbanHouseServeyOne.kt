package com.example.calculatersunotes.ui.urban.house

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import com.example.calculatersunotes.databinding.FragmentUrbanHouseServeyOneBinding
import java.text.ParseException

class UrbanHouseServeyOne : Fragment() {
    private var _binding: FragmentUrbanHouseServeyOneBinding? = null

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

        _binding = FragmentUrbanHouseServeyOneBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        swipeNext()

        buttonList = mutableListOf(
            binding.noShowerBtn,
            binding.yesShowerBtn,
            binding.hasElectricityMeterBtn,
            binding.withoutElectricityMeterBtn
        )

        exceptionButtonList = mutableListOf(
            binding.noShowerBtn,
            binding.withoutElectricityMeterBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        binding.backButton.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager, R.id.fragmentContainer, Urban(), "left")
        }

        binding.noShowerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noShowerBtn , binding.yesShowerBtn)
            urbanHouseViewModel.updateShowerPossession(false)
        }

        binding.yesShowerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesShowerBtn , binding.noShowerBtn)
            urbanHouseViewModel.updateShowerPossession(true)
        }

        binding.hasElectricityMeterBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.hasElectricityMeterBtn , binding.withoutElectricityMeterBtn)
            urbanHouseViewModel.updateElectricityMeterPossession(true)
        }

        binding.withoutElectricityMeterBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.withoutElectricityMeterBtn , binding.hasElectricityMeterBtn)
            urbanHouseViewModel.updateElectricityMeterPossession(false)
        }

        binding.roomsNumberInput.addTextChangedListener(object : TextWatcher {
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
                urbanHouseViewModel.updateRoomsNumber(number)
            }
        })

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

}