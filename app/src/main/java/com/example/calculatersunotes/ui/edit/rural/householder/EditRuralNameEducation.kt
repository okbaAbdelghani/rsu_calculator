package com.example.calculatersunotes.ui.edit.rural.householder

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.viewpager.widget.ViewPager
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentEditNameEducationBinding
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.lang.NumberFormatException

class EditRuralNameEducation : Fragment() {
    private var _binding : FragmentEditNameEducationBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseHolderViewModel: RuralHouseHolderViewModel by activityViewModels()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private lateinit var editHouseHolderAdapter: EditRuralHouseholderAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNameEducationBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) {

            val hasBasicEducation = it.householder.hasBasicEducation

            buttonList = mutableListOf(
                binding.basicEducationBtn,
                binding.withoutBtn
            )

            val fullName = it.householder.fullName
            val age = it.householder.age

            binding.fullNameInput.setText(fullName)
            binding.ageInput.setText(age.toString())

            if(hasBasicEducation) {
                fragmentUtil.setInactiveButtonColors(buttonList, binding.basicEducationBtn)
            } else {
                fragmentUtil.setInactiveButtonColors(buttonList, binding.withoutBtn)
            }
        }

        binding.basicEducationBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList, binding.basicEducationBtn)
            ruralHouseHolderViewModel.updateEducationLevel(true)
        }

        binding.withoutBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList, binding.withoutBtn)
            ruralHouseHolderViewModel.updateEducationLevel(false)
        }

        //Update Householder age
        binding.ageInput.addTextChangedListener(object : TextWatcher {
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
        binding.fullNameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                ruralHouseHolderViewModel.updateName(enteredValue)
            }
        })

        binding.includedNext.nextBtn.setOnClickListener {
            val parenFragment: EditRuralHouseholder = parentFragment as EditRuralHouseholder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_householder_pager)

            editHouseHolderAdapter = EditRuralHouseholderAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseHolderAdapter) {}
        }

        return binding.root
    }
}