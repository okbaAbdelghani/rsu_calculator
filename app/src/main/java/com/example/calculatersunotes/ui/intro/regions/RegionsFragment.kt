package com.example.calculatersunotes.ui.intro.regions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import com.example.calculatersunotes.R


class RegionsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_regions, container, false)
        val spinner: Spinner = rootView.findViewById<Spinner>(R.id.regions_spinner)
        /*
        val data = resources.getStringArray(R.array.regions).toList()
        val adapter = CustomSpinnerAdapter(requireContext(),R.id.spinnerItemText, data)
        spinner.adapter = adapter
         */
        return rootView
    }

}