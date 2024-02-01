package com.example.calculatersunotes.ui.result

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.rural.house.RuralHouseFragment
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.ui.urban.house.UrbanHouseViewModel
import com.example.calculatersunotes.utils.FragmentUtil

class ResultFragment : Fragment() {
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private val urbanHouseViewModel: UrbanHouseViewModel by activityViewModels()
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
    private lateinit var fragmentUtil: FragmentUtil
    private var selectedEnv = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_result, container, false)

        fragmentUtil = FragmentUtil(requireContext())





        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            selectedEnv = env
            val rootView = inflater.inflate(R.layout.fragment_result, container, false)

            when (env) {
                "urban" -> {
                    observeUrbanHouse()
                    observeUrbanFamilyResult(rootView)
                }
                "rural" -> {
                    observeRuralHouse()
                    observeRuralFamilyResult(rootView)
                }
            }
        })

        // Inflate the layout for this fragment
        goBack(rootView)
        navigateToEdit(rootView)
        return rootView
    }

    private fun goBack(view: View){
        val backBtn = view.findViewById<ImageButton>(R.id.back_button)
        backBtn.setOnClickListener{
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, RuralHouseFragment())
        }
    }

    private fun navigateToEdit(view: View){
        val editBtn = view.findViewById<Button>(R.id.edit_btn)
        editBtn.setOnClickListener{
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }
    }

    private fun observeUrbanHouse() {
        urbanHouseViewModel.urbanHouse.observe(viewLifecycleOwner) { householder ->
            urbanFamilyViewModel.updateFamilyHouse(householder)
            urbanFamilyViewModel.createSurveyItems(requireContext())
        }
    }

    private fun observeRuralHouse() {
        ruralHouseViewModel.ruralHouse.observe(viewLifecycleOwner) { householder ->
            ruralFamilyViewModel.updateFamilyHouse(householder)
            ruralFamilyViewModel.createSurveyItems(requireContext())
        }
    }

    private fun observeUrbanFamilyResult(rootView: View) {
        urbanFamilyViewModel.result.observe(viewLifecycleOwner) { result ->
            animateResult(result, rootView )
        }
    }

    private fun observeRuralFamilyResult(rootView: View) {

        ruralFamilyViewModel.result.observe(viewLifecycleOwner) { result ->
            animateResult(result, rootView)
        }
    }

    private fun animateResult(result: Double, rootView: View) {
        val resultTxt = rootView.findViewById<TextView>(R.id.result_txt)
        val valueAnimator = ValueAnimator.ofFloat(0f, result.toFloat())
        valueAnimator.duration = 2000

        valueAnimator.addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Float
            resultTxt.text = String.format("%.2f", animatedValue)
        }
        valueAnimator.start()
    }

}