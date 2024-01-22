package com.example.calculatersunotes.ui.rural.householder

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.R
import java.lang.NumberFormatException

class RuralNameEducation : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentUtil = FragmentUtil(requireContext())
        val view = inflater.inflate(R.layout.fragment_rural_name_education, container, false)

        val basicEducationBtn = view.findViewById<Button>(R.id.basic_education_btn)
        val withoutBtn = view.findViewById<Button>(R.id.without_btn)
        val ageInput = view.findViewById<EditText>(R.id.age_input)
        val fullNameInput = view.findViewById<EditText>(R.id.full_name_input)

        buttonList += basicEducationBtn
        buttonList += withoutBtn

        fragmentUtil.setInactiveButtonColors(buttonList, withoutBtn)

        basicEducationBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList,basicEducationBtn)
            ruralHouseHolderViewModel.updateEducationLevel(true)
        }

        withoutBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList,withoutBtn)
            ruralHouseHolderViewModel.updateEducationLevel(false)
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
        fullNameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                    val enteredValue = s.toString()
                    ruralHouseHolderViewModel.updateName(enteredValue)
            }
        })

        return view
    }

}