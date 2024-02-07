package com.example.calculatersunotes.ui.urban.house

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
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
   // private var _binding: FragmentUrbanHouseServeyOneBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseViewModel: UrbanHouseViewModel by activityViewModels()
    private lateinit var housePagerAdapter: UrbanHousePagerAdapter
    private lateinit var swipeUtil: SwipeUtil

    //private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_urban_house_servey_one, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        val roomsNumberInput = rootView.findViewById<EditText>(R.id.rooms_number_input)

        val noShowerBtn = rootView.findViewById<Button>(R.id.no_shower_btn)
        val yesShowerBtn = rootView.findViewById<Button>(R.id.yes_shower_btn)
        val hasElectricityMeterBtn = rootView.findViewById<Button>(R.id.has_electricity_meter_btn)
        val withoutElectricityMeterBtn = rootView.findViewById<Button>(R.id.without_electricity_meter_btn)

        swipeNext(rootView)

        buttonList = mutableListOf(
            noShowerBtn,
            yesShowerBtn,
            hasElectricityMeterBtn,
            withoutElectricityMeterBtn
        )

        exceptionButtonList = mutableListOf(
            noShowerBtn,
            withoutElectricityMeterBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        backBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager, R.id.fragmentContainer, Urban())
        }

        noShowerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noShowerBtn , yesShowerBtn)
            urbanHouseViewModel.updateShowerPossession(false)
        }

        yesShowerBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesShowerBtn , noShowerBtn)
            urbanHouseViewModel.updateShowerPossession(true)
        }

        hasElectricityMeterBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(hasElectricityMeterBtn , withoutElectricityMeterBtn)
            urbanHouseViewModel.updateElectricityMeterPossession(true)
        }

        withoutElectricityMeterBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(withoutElectricityMeterBtn , hasElectricityMeterBtn)
            urbanHouseViewModel.updateElectricityMeterPossession(false)
        }

        roomsNumberInput.addTextChangedListener(object : TextWatcher {
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

}