package com.example.calculatersunotes.ui.edit.householder

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
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.lang.NumberFormatException

class EditNameEducation : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var editHouseHolderAdapter: EditHouseholderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit_name_education, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val nameInput = rootView.findViewById<EditText>(R.id.full_name_input)
        val ageInput = rootView.findViewById<EditText>(R.id.age_input)
        val basicEducationBtn = rootView.findViewById<Button>(R.id.basic_education_btn)
        val withoutBtn = rootView.findViewById<Button>(R.id.without_btn)

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            // set selected item for spinner

            val hasBasicEducation = family.householder.hasBasicEducation

            buttonList += basicEducationBtn
            buttonList += withoutBtn

            val fullName = family.householder.fullName
            val age = family.householder.age

            nameInput.setText(fullName)
            ageInput.setText(age.toString())

            if(hasBasicEducation) {
                fragmentUtil.setInactiveButtonColors(buttonList, basicEducationBtn)
            } else {
                fragmentUtil.setInactiveButtonColors(buttonList, withoutBtn)
            }


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
                    ruralHouseHolderViewModel.updateAge(age)
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
                ruralHouseHolderViewModel.updateName(enteredValue)
            }
        })

        nextBtn.setOnClickListener {
            val parenFragment: EditHouseholder = parentFragment as EditHouseholder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_householder_pager)

            editHouseHolderAdapter = EditHouseholderAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseHolderAdapter) {}
        }

        return rootView
    }
}