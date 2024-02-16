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
import com.example.calculatersunotes.databinding.FragmentAddUrbanMemberBinding
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.rural.house.RuralHouseFragment
import com.example.calculatersunotes.utils.FragmentUtil
import java.lang.NumberFormatException

class AddUrbanMember : Fragment() {
    private var _binding : FragmentAddUrbanMemberBinding? = null

    private lateinit var fragmentUtil: FragmentUtil
    private val urbanMemberViewModel: UrbanMemberViewModel by activityViewModels()
    private var exceptionButtonList: List<Button> = mutableListOf()
    private var buttonList: List<Button> = mutableListOf()

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddUrbanMemberBinding.inflate(inflater, container, false)
        fragmentUtil = FragmentUtil(requireContext())

        binding.noHealthSecurityBtn.setOnClickListener {
            urbanMemberViewModel.updateHealthSecurityPossession(false)
            fragmentUtil.toggleTwoOptions(binding.noHealthSecurityBtn, binding.yesHealthSecurityBtn)
        }

        binding.yesHealthSecurityBtn.setOnClickListener {
            urbanMemberViewModel.updateHealthSecurityPossession(true)
            fragmentUtil.toggleTwoOptions(binding.yesHealthSecurityBtn, binding.noHealthSecurityBtn)
        }

        buttonList = mutableListOf(
            binding.noHealthSecurityBtn,
            binding.yesHealthSecurityBtn
        )

        exceptionButtonList = mutableListOf(
            binding.noHealthSecurityBtn,
        )

        fragmentUtil.setDefaultActiveButtons(buttonList, exceptionButtonList)

        var ageValue = 0

        binding.ageInput.addTextChangedListener(object : TextWatcher {
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

        binding.fullNameInput.addTextChangedListener(object : TextWatcher {
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

        binding?.nextBtn?.nextBtn?.setOnClickListener {
            if(ageValue >= 15) {
                navigateToMemberPlus15()
            } else {
                navigateToMemberMinus15()
            }

        }

        goBack(binding?.backButton?.backBtn)
        return binding.root
    }

    private fun navigateToMemberMinus15(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMemberMinus())
    }

    private fun navigateToMemberPlus15(){
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMemberPlus())
    }

    private fun goBack(btn: ImageButton?){
        btn?.setOnClickListener{
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit(), "left")
        }
    }

}