package com.example.calculatersunotes.ui.edit.urban.householder

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
import com.example.calculatersunotes.databinding.FragmentEditUrbanNameEducationBinding
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.urban.householder.UrbanHouseHolderViewModel
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.utils.SwipeUtil
import java.lang.NumberFormatException

class EditUrbanNameEducation : Fragment() {
    private var _binding : FragmentEditUrbanNameEducationBinding? = null

    private var buttonList: MutableList<Button> = mutableListOf()
    private lateinit var swipeUtil: SwipeUtil
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var editHouseHolderAdapter: EditUrbanHouseHolderAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditUrbanNameEducationBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        urbanFamilyViewModel.family.observe(viewLifecycleOwner) {
            val hasHighEducation = it.householder.hasHighEducationLevel

            buttonList = mutableListOf(
                binding.highEducationBtn,
                binding.withoutBtn
            )

            val fullName = it.householder.fullName
            val age = it.householder.age

            binding.fullNameInput.setText(fullName)
            binding.ageInput.setText(age.toString())

            if(hasHighEducation) {
                fragmentUtil.setInactiveButtonColors(buttonList, binding.highEducationBtn)
            } else {
                fragmentUtil.setInactiveButtonColors(buttonList, binding.withoutBtn)
            }

        }

        binding.highEducationBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList, binding.highEducationBtn)
            urbanHouseHolderViewModel.updateEducationLevel(true)
        }

        binding.withoutBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList, binding.withoutBtn)
            urbanHouseHolderViewModel.updateEducationLevel(false)
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
                    urbanHouseHolderViewModel.updateAge(age)
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
                urbanHouseHolderViewModel.updateName(enteredValue)
            }
        })

        binding.includedNext.nextBtn.setOnClickListener {
            val parenFragment: EditUrbanHouseHolder = parentFragment as EditUrbanHouseHolder
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.edit_urban_householder_pager)

            editHouseHolderAdapter = EditUrbanHouseHolderAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, editHouseHolderAdapter) {}
        }

        return binding.root
    }

}