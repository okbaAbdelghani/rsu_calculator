package com.example.calculatersunotes.ui.rural.householder

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
import com.example.calculatersunotes.ui.edit.rural.house.EditRuralHouse
import com.example.calculatersunotes.ui.edit.rural.house.EditRuralHousePagerAdapter
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil

class RuralSocialStatus : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var swipeUtil: SwipeUtil
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private lateinit var editHousePagerAdapter: EditRuralHousePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_rural_social_status, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val machineryManagerBtn = view.findViewById<Button>(R.id.machinery_manager_btn)
        val craftsmanBtn = view.findViewById<Button>(R.id.craftsman_btn)
        val farmerBtn = view.findViewById<Button>(R.id.farmer_btn)
        val otherBtn = view.findViewById<Button>(R.id.other_btn)
        val recruiterBtn = view.findViewById<Button>(R.id.recruiter_btn)
        val withoutBtn = view.findViewById<Button>(R.id.without_btn)
        val highPositionBtn = view.findViewById<Button>(R.id.high_position_btn)

        val socialStatusButtons = listOf<Button>(
            machineryManagerBtn,
            highPositionBtn,
            craftsmanBtn,
            farmerBtn,
            otherBtn
        )

        val positionInJobButtons = listOf<Button>(
            recruiterBtn,
            withoutBtn,
        )

        buttonList = mutableListOf(
            machineryManagerBtn,
            craftsmanBtn,
            farmerBtn,
            otherBtn,
            recruiterBtn,
            highPositionBtn,
            withoutBtn
        )

        machineryManagerBtn.setOnClickListener {
            setHouseHolderAsMachineryManager()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, machineryManagerBtn)
        }

        craftsmanBtn.setOnClickListener {
            setHouseHolderAsCraftsman()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, craftsmanBtn)
        }

        farmerBtn.setOnClickListener {
            setHouseHolderAsFarmer()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, farmerBtn)
        }

        highPositionBtn.setOnClickListener {
            setHouseHolderAsHighProfessionalPosition()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, highPositionBtn)
        }

        otherBtn.setOnClickListener {
            setHouseHolderAsNone()
            fragmentUtil.setInactiveButtonColors(socialStatusButtons, otherBtn)
        }

        recruiterBtn.setOnClickListener {
            setHouseHolderAsRecruiter()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, recruiterBtn)
        }

        withoutBtn.setOnClickListener {
            setHouseHolderAsJobless()
            fragmentUtil.setInactiveButtonColors(positionInJobButtons, withoutBtn)
        }

        exceptionButtonList = mutableListOf(
            otherBtn,
            withoutBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        return view
    }


    private fun setHouseHolderAsFarmer(){
        ruralHouseHolderViewModel.updateSocialStatus("farmer")
    }
    private fun setHouseHolderAsCraftsman(){
        ruralHouseHolderViewModel.updateSocialStatus("craftsman")
    }
    private fun setHouseHolderAsMachineryManager(){
        ruralHouseHolderViewModel.updateSocialStatus("machinery_manager")
    }
    private fun setHouseHolderAsHighProfessionalPosition(){
        ruralHouseHolderViewModel.updateSocialStatus("high_position")
    }
    private fun setHouseHolderAsNone(){
        ruralHouseHolderViewModel.updateSocialStatus("none")
    }
    private fun setHouseHolderAsRecruiter(){
        ruralHouseHolderViewModel.updateJobPosition("employer")
    }
    private fun setHouseHolderAsJobless(){
        ruralHouseHolderViewModel.updateJobPosition("without")
    }

    private fun swipeBack(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: EditRuralHouse = parentFragment as EditRuralHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHousePagerAdapter = EditRuralHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateBack(viewPager, editHousePagerAdapter)
        }
    }

    private fun swipeNext(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: EditRuralHouse = parentFragment as EditRuralHouse
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_house_container)

            editHousePagerAdapter = EditRuralHousePagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHousePagerAdapter) {}
        }
    }

}