package com.example.calculatersunotes.ui.rural.members

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
import com.example.calculatersunotes.ui.urban.members.AddUrbanMemberMinus
import com.example.calculatersunotes.utils.FragmentUtil

class AddRuralMember : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil
    private val ruralMemberViewModel: RuralMemberViewModel by activityViewModels()
    private var buttonList: List<Button> = mutableListOf()
    private var exceptionButtonList: List<Button> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentUtil = FragmentUtil(requireContext())
        val rootView = inflater.inflate(R.layout.fragment_add_rural_member, container, false)

        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)
        val ageInput = rootView.findViewById<EditText>(R.id.age_input)
        val nameInput = rootView.findViewById<EditText>(R.id.full_name_input)

        val noHealthSecurityBtn = rootView.findViewById<Button>(R.id.no_having_health_security_btn)
        val yesHealthSecurityBtn = rootView.findViewById<Button>(R.id.yes_having_health_security_btn)

        buttonList = listOf(
            noHealthSecurityBtn,
            yesHealthSecurityBtn
        )

        exceptionButtonList = listOf(
            noHealthSecurityBtn
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        ageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                var number = 0u
                number = if( enteredValue == "") {
                    0u
                } else {
                    enteredValue.toUInt()
                }

                ruralMemberViewModel.updateMemberAge(number.toUShort())
            }
        })

        nameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                ruralMemberViewModel.updateMemberName(enteredValue)
            }
        })

        nextBtn.setOnClickListener {
            navigateToMemberDetails()
        }

        noHealthSecurityBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(noHealthSecurityBtn, yesHealthSecurityBtn)
            ruralMemberViewModel.updateHealthSecurityPossession(false)
        }

        yesHealthSecurityBtn.setOnClickListener {
            fragmentUtil.toggleTwoOptions(yesHealthSecurityBtn, noHealthSecurityBtn)
            ruralMemberViewModel.updateHealthSecurityPossession(true)
        }

        return rootView
    }

    private fun navigateToMemberDetails() {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddRuralMemberDetails())
    }

}