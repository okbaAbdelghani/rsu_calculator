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
import com.example.calculatersunotes.databinding.FragmentUrbanHouseServeyThreeBinding
import com.example.calculatersunotes.databinding.FragmentUrbanHouseServeyTwoBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.congrats.HouseDoneCongrats
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil


class UrbanHouseServeyThree : Fragment() {
    private var _binding: FragmentUrbanHouseServeyThreeBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseViewModel: UrbanHouseViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
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
        _binding = FragmentUrbanHouseServeyThreeBinding.inflate(inflater, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        buttonList = mutableListOf(
            binding.yesAntennaBtn,
            binding.noAntennaBtn,
            binding.yesFixBtn,
            binding.noFixBtn,
            binding.yesLoanBtn,
            binding.noLoanBtn,
            binding.yesSecondHouseBtn,
            binding.noSecondHouseBtn
        )

        exceptionButtonList = mutableListOf(
            binding.noAntennaBtn,
            binding.noFixBtn,
            binding.noLoanBtn,
            binding.noSecondHouseBtn
        )

        binding.yesAntennaBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesAntennaBtn , binding.noAntennaBtn)
            urbanHouseViewModel.updateAntennaPossession(true)
        }

        binding.noAntennaBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noAntennaBtn , binding.yesAntennaBtn)
            urbanHouseViewModel.updateAntennaPossession(false)
        }

        binding.yesFixBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesFixBtn , binding.noFixBtn)
            urbanHouseViewModel.updateFixPossession(true)
        }

        binding.noFixBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noFixBtn , binding.yesFixBtn)
            urbanHouseViewModel.updateFixPossession(false)
        }

        binding.yesLoanBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesLoanBtn , binding.noLoanBtn)
            urbanHouseViewModel.updateLoanPossession(true)
        }

        binding.noLoanBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noLoanBtn , binding.yesLoanBtn)
            urbanHouseViewModel.updateLoanPossession(false)
        }

        binding.yesSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.yesSecondHouseBtn , binding.noSecondHouseBtn)
            urbanFamilyViewModel.updateSecondHousePossession(true)
        }


        binding.noSecondHouseBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(binding.noSecondHouseBtn , binding.yesSecondHouseBtn)
            urbanFamilyViewModel.updateSecondHousePossession(false)
        }

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        swipeBack()
        swipeNext()

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
