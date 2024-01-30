package com.example.calculatersunotes.ui.intro.environments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.utils.FragmentUtil
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel


class EnvironmentsFragment : Fragment() {
    private var buttonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private var selectedEnv: String = ""
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_environments, container, false)


        fragmentUtil = FragmentUtil(requireContext())

        val ruralBtn = rootView.findViewById<Button>(R.id.rural_btn)
        val urbanBtn = rootView.findViewById<Button>(R.id.urban_btn)

        buttonList += ruralBtn
        buttonList += urbanBtn

        fragmentUtil.setInactiveButtonColors(buttonList, null)


        ruralBtn.setOnClickListener {
            selectedEnv = "rural"
            setEnvironment(selectedEnv)
            fragmentUtil.setInactiveButtonColors(buttonList, ruralBtn)
        }

        urbanBtn.setOnClickListener {
            selectedEnv = "urban"
            setEnvironment(selectedEnv)
            fragmentUtil.setInactiveButtonColors(buttonList, urbanBtn)
        }

        return rootView
    }

    private fun setEnvironment(value: String) {
        environmentViewModel.updateEnvironment(value)

    }

}