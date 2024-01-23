package com.example.calculatersunotes.ui.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel

class ResultFragment : Fragment() {
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        ruralFamilyViewModel.createSurveyItems(requireContext())
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

}