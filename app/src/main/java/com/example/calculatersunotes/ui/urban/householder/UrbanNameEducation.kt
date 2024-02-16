package com.example.calculatersunotes.ui.urban.householder

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
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentUrbanNameEducationBinding
import com.example.calculatersunotes.ui.intro.EnvRegionContainerFrag
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.SwipeUtil
import java.lang.NumberFormatException

class UrbanNameEducation : Fragment() {
    private var _binding : FragmentUrbanNameEducationBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var urbanPagerAdapter: UrbanPagerAdapter
    private lateinit var swipeUtil: SwipeUtil
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        _binding = FragmentUrbanNameEducationBinding.inflate(inflater, container, false)

        buttonList = listOf(
            binding.highEducationBtn,
            binding.withoutBtn
        )

        fragmentUtil.setInactiveButtonColors(buttonList, binding.withoutBtn)

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

        swipeNext()
        onBackBtnClicked()

        return binding.root
    }

    private fun swipeNext() {
        binding.included.nextBtn.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, urbanPagerAdapter) {}
        }
    }

    private fun onBackBtnClicked() {
        binding.backBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, EnvRegionContainerFrag(),"left")
            }
        }
    }

