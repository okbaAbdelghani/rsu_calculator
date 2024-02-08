package com.example.calculatersunotes.ui.intro.regions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.calculatersunotes.R
import com.example.calculatersunotes.data.models.Region
import com.example.calculatersunotes.data.models.SurveyItem
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


class RegionsFragment : Fragment() {
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
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

        val stringResult = readJsonFromAssets("json/regions.json", requireContext())
        val regions = parseJsonToModel(stringResult)

        val regionsNames = regions.map { it.name}

        val adapter =  ArrayAdapter(requireContext(), R.layout.region_spinner_item, regionsNames)
        adapter.setDropDownViewResource(R.layout.region_spinner_item)

        spinner.adapter = adapter
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
                    val selectedRegionName = regions[position]
                    when (env) {
                        "urban" -> {
                            updateUrbanRegion(selectedRegionName)
                        }
                        "rural" -> {
                           updateRuralRegion(selectedRegionName)
                        }
                    }
                })

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle case where nothing is selected (if needed)
            }
        })


        return rootView
    }

    private fun parseJsonToModel(jsonString: String?): MutableList<Region> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<MutableList<Region>>() {}.type)
    }

    private fun readJsonFromAssets(fileName: String, context: Context): String? {
        return try {
            context.assets.open(fileName).use { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                String(buffer)}
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun updateUrbanRegion(selectedRegionName: Region) {
        urbanFamilyViewModel.updateFamilyRegion(selectedRegionName)
    }

    private fun updateRuralRegion(selectedRegionName: Region) {
        ruralFamilyViewModel.updateFamilyRegion(selectedRegionName)
    }

}