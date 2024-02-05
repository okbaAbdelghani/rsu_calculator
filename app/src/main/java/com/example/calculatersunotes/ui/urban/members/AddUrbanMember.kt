package com.example.calculatersunotes.ui.urban.members

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
import com.example.calculatersunotes.R
import com.example.calculatersunotes.utils.FragmentUtil
import java.lang.NumberFormatException

class AddUrbanMember : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanMemberViewModel: UrbanMemberViewModel by activityViewModels()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private var buttonList: List<Button> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentUtil = FragmentUtil(requireContext())
        val rootView = inflater.inflate(R.layout.fragment_add_urban_member, container, false)

        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)
        val ageInput = rootView.findViewById<EditText>(R.id.age_input)
        val fullNameInput = rootView.findViewById<EditText>(R.id.full_name_input)

        val noHealthSecurityBtn = rootView.findViewById<Button>(R.id.no_health_security_btn)
        val yesHealthSecurityBtn = rootView.findViewById<Button>(R.id.yes_health_security_btn)

        noHealthSecurityBtn.setOnClickListener {
            urbanMemberViewModel.updateHealthSecurityPossession(false)
            fragmentUtil.toggleTwoOptions(noHealthSecurityBtn, yesHealthSecurityBtn)
        }

        yesHealthSecurityBtn.setOnClickListener {
            urbanMemberViewModel.updateHealthSecurityPossession(true)
            fragmentUtil.toggleTwoOptions(yesHealthSecurityBtn, noHealthSecurityBtn)
        }

        buttonList = mutableListOf(
            noHealthSecurityBtn,
            yesHealthSecurityBtn
        )

        exceptionButtonList = mutableListOf(
            noHealthSecurityBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        var ageValue = 0

        ageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                try {
                    var number = if( enteredValue == "") {
                        0u
                    } else {
                        enteredValue.toUInt()
                    }

                    ageValue = enteredValue.toInt()
                    urbanMemberViewModel.updateMemberAge(number.toUShort())
                } catch (e: NumberFormatException) {
                    println("Invalid input string")
                }
            }
        })

        fullNameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    val enteredValue = s.toString()
                    urbanMemberViewModel.updateMemberName(enteredValue)
                } catch (e: NumberFormatException) {
                    println("Invalid input string")
                }
            }
        })

        nextBtn.setOnClickListener {
            if(ageValue >= 15) {
                navigateToMemberPlus15()
            } else {
                navigateToMemberMinus15()
            }

        }
        return rootView
    }

    private fun navigateToMemberMinus15(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMemberMinus())
    }

    private fun navigateToMemberPlus15(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMemberPlus())
    }

}