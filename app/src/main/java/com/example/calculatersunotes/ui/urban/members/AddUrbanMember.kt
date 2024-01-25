package com.example.calculatersunotes.ui.urban.members

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.example.calculatersunotes.R
import com.example.calculatersunotes.utils.FragmentUtil

class AddUrbanMember : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil


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
        var ageValue = 0

        ageInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                val number = enteredValue.toUInt()
                    ageValue = number.toInt()
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