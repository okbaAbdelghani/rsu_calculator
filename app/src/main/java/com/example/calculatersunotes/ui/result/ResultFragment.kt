package com.example.calculatersunotes.ui.result

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel

class ResultFragment : Fragment() {
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val rootView = inflater.inflate(R.layout.fragment_result, container, false)

        val resultTxt = rootView.findViewById<TextView>(R.id.result_txt)

        ruralHouseViewModel.ruralHouse.observe(viewLifecycleOwner) { householder ->
            ruralFamilyViewModel.updateFamilyHouse(householder)
            ruralFamilyViewModel.createSurveyItems(requireContext())
        }

        ruralFamilyViewModel.result.observe(viewLifecycleOwner) { result ->

            val valueAnimator = ValueAnimator.ofFloat(0f, result.toFloat())
            valueAnimator.duration = 2000

            valueAnimator.addUpdateListener {animator ->
                val animatedValue = animator.animatedValue as Float
                resultTxt.text = String.format("%.2f", animatedValue)
            }
            valueAnimator.start()
        }
        // Inflate the layout for this fragment
        goBack(rootView)
        return rootView
    }

    private fun goBack(view: View){
        val backBtn = view.findViewById<ImageButton>(R.id.back_button)
        backBtn.setOnClickListener{
            activity?.onBackPressed()
        }
    }

}