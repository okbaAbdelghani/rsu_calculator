package com.example.calculatersunotes.ui.urban.house

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
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.congrats.HouseDoneCongrats
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class UrbanHouseServeyThree : Fragment() {
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
        val rootView = inflater.inflate(R.layout.fragment_urban_house_servey_three, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        val yesAntennaBtn = rootView.findViewById<Button>(R.id.yes_antenna_btn)
        val noAntennaBtn = rootView.findViewById<Button>(R.id.no_antenna_btn)
        val yesFixBtn = rootView.findViewById<Button>(R.id.yes_fix_btn)
        val noFixBtn = rootView.findViewById<Button>(R.id.no_fix_btn)
        val yesLoanBtn = rootView.findViewById<Button>(R.id.yes_loan_btn)
        val noLoanBtn = rootView.findViewById<Button>(R.id.no_loan_btn)
        val yesSecondHouseBtn = rootView.findViewById<Button>(R.id.yes_second_house_btn)
        val noSecondHouseBtn = rootView.findViewById<Button>(R.id.no_second_house_btn)

        buttonList = mutableListOf(
            yesAntennaBtn,
            noAntennaBtn,
            yesFixBtn,
            noFixBtn,
            yesLoanBtn,
            noLoanBtn,
            yesSecondHouseBtn,
            noSecondHouseBtn
        )

        exceptionButtonList = mutableListOf(
            noAntennaBtn,
            noFixBtn,
            noLoanBtn,
            noSecondHouseBtn
        )

        yesAntennaBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesAntennaBtn , noAntennaBtn)
            urbanHouseViewModel.updateAntennaPossession(true)
        }

        noAntennaBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noAntennaBtn , yesAntennaBtn)
            urbanHouseViewModel.updateAntennaPossession(false)
        }

        yesFixBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesFixBtn , noFixBtn)
            urbanHouseViewModel.updateFixPossession(true)
        }

        noFixBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noFixBtn , yesFixBtn)
            urbanHouseViewModel.updateFixPossession(false)
        }

        yesLoanBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesLoanBtn , noLoanBtn)
            urbanHouseViewModel.updateLoanPossession(true)
        }

        noLoanBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noLoanBtn , yesLoanBtn)
            urbanHouseViewModel.updateLoanPossession(false)
        }

        yesSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesSecondHouseBtn , noSecondHouseBtn)
            urbanFamilyViewModel.updateSecondHousePossession(true)
        }


        noSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noSecondHouseBtn , yesSecondHouseBtn)
            urbanFamilyViewModel.updateSecondHousePossession(false)
        }

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        swipeBack(backBtn)
        swipeNext(rootView)

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
