package com.example.calculatersunotes.ui.edit.urban.householder

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
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.urban.householder.UrbanHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.lang.NumberFormatException

class EditUrbanNameEducation : Fragment() {
    private var buttonList: MutableList<Button> = mutableListOf()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var editHouseHolderAdapter: EditUrbanHouseHolderAdapter

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_urban_name_education, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val nameInput = rootView.findViewById<EditText>(R.id.full_name_input)
        val ageInput = rootView.findViewById<EditText>(R.id.age_input)
        val highEducationBtn = rootView.findViewById<Button>(R.id.high_education_btn)
        val withoutBtn = rootView.findViewById<Button>(R.id.without_btn)

        urbanFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val hasHighEducation = family.householder.hasHighEducationLevel

            buttonList = mutableListOf(
                highEducationBtn,
                withoutBtn
            )

            val fullName = family.householder.fullName
            val age = family.householder.age

            nameInput.setText(fullName)
            ageInput.setText(age.toString())

            if(hasHighEducation) {
                fragmentUtil.setInactiveButtonColors(buttonList, highEducationBtn)
            } else {
                fragmentUtil.setInactiveButtonColors(buttonList, withoutBtn)
            }

        }

        highEducationBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList,highEducationBtn)
            urbanHouseHolderViewModel.updateEducationLevel(true)
        }

        withoutBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList,withoutBtn)
            urbanHouseHolderViewModel.updateEducationLevel(false)
        }


        //Update Householder age
        ageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    val enteredValue = s.toString()
                    val age = enteredValue.toUShort()
                    urbanHouseHolderViewModel.updateAge(age)
                } catch (e: NumberFormatException) {
                    println("Invalid input string")
                }

            }
        })

        //Update householder name
        nameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                urbanHouseHolderViewModel.updateName(enteredValue)
            }
        })

        nextBtn.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseHolderAdapter) {}
        }

        return rootView
    }

}