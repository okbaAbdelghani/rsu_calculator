package com.example.calculatersunotes.ui.urban.householder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.R

class UrbanNameEducation : Fragment() {

    private var buttonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUtil = FragmentUtil(requireContext())
        // Inflate the layout for this fragment

        /*
        val basicEducationBtn = view.findViewById<Button>(R.id.basic_education_btn)
        val withoutBtn = view.findViewById<Button>(R.id.without_btn)

        buttonList += basicEducationBtn
        buttonList += withoutBtn

        fragmentUtil.setInactiveButtonColors(buttonList, withoutBtn)

        basicEducationBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList, basicEducationBtn)
        }

        withoutBtn.setOnClickListener {
            fragmentUtil.setInactiveButtonColors(buttonList, withoutBtn)
        }
         */

        return inflater.inflate(R.layout.fragment_urban_name_education, container, false)
    }

}