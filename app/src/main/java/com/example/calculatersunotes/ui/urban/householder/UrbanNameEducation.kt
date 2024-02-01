package com.example.calculatersunotes.ui.urban.householder

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
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.rural.householder.RuralHouseHolderViewModel
import com.example.calculatersunotes.ui.urban.Urban
import com.example.calculatersunotes.ui.urban.UrbanPagerAdapter
import com.example.calculatersunotes.utils.SwipeUtil
import java.lang.NumberFormatException

class UrbanNameEducation : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private val urbanHouseHolderViewModel: UrbanHouseHolderViewModel by activityViewModels()
    private lateinit var urbanPagerAdapter: UrbanPagerAdapter
    private lateinit var swipeUtil: SwipeUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUtil = FragmentUtil(requireContext())
        swipeUtil = SwipeUtil()

        val rootView = inflater.inflate(R.layout.fragment_urban_name_education, container, false)

        val nextBtn = rootView.findViewById<ImageButton>(R.id.next_btn)

        val basicEducationBtn = rootView.findViewById<Button>(R.id.high_education_btn)
        val withoutBtn = rootView.findViewById<Button>(R.id.without_btn)
        val ageInput = rootView.findViewById<EditText>(R.id.age_input)
        val fullNameInput = rootView.findViewById<EditText>(R.id.full_name_input)

        buttonList += basicEducationBtn
        buttonList += withoutBtn

        fragmentUtil.setInactiveButtonColors(buttonList, withoutBtn)

        basicEducationBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList,basicEducationBtn)
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
        fullNameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val enteredValue = s.toString()
                urbanHouseHolderViewModel.updateName(enteredValue)
            }
        })

        swipeNext(nextBtn)

        return rootView
    }

    private fun swipeNext(button: ImageButton) {
        button.setOnClickListener {
            val parenFragment: Urban = parentFragment as Urban
            val viewPager: ViewPager? = parenFragment.view?.findViewById(R.id.questions_container)

            urbanPagerAdapter = UrbanPagerAdapter(childFragmentManager)
            swipeUtil.navigateNext(viewPager, urbanPagerAdapter) {}
        }
    }

}