package com.example.calculatersunotes.ui.intro.regions

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

        val regions = parseJsonToModel(regionsJson())
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

    private fun regionsJson(): String {
        return "[\n" +
                "  {\n" +
                "    \"code\": \"R1\",\n" +
                "    \"name\": \"طنجة - تطوان - الحسيمة\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"منطقة\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.107,\n" +
                "    \"kzgRural\": 0.420\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R10\",\n" +
                "    \"name\": \"كلميم - واد نون\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.285,\n" +
                "    \"kzgRural\": 0.318\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R11\",\n" +
                "    \"name\": \"العيون - الساقية الحمراء\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.285,\n" +
                "    \"kzgRural\": 0.318\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R12\",\n" +
                "    \"name\": \"الداخلة - وادي الذهب\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.285,\n" +
                "    \"kzgRural\": 0.318\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R2\",\n" +
                "    \"name\": \"الشرق\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.196,\n" +
                "    \"kzgRural\": 0.230\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R3\",\n" +
                "    \"name\": \"فاس - مكناس\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.123,\n" +
                "    \"kzgRural\": 0.296\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R4\",\n" +
                "    \"name\": \"الرباط -سلا -القنيطرة\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"منطقة\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.0555,\n" +
                "    \"kzgRural\": 0.302\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R5\",\n" +
                "    \"name\": \"بني ملال - خنيفرة\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0,\n" +
                "    \"kzgRural\": 0.351\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R6\",\n" +
                "    \"name\": \"الدار البيضاء - سطات\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.220,\n" +
                "    \"kzgRural\": 0.464\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R7\",\n" +
                "    \"name\": \"مراكش - آسفي\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0.194,\n" +
                "    \"kzgRural\": 0.377\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R8\",\n" +
                "    \"name\": \"درعة - تافيلالت\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0,\n" +
                "    \"kzgRural\": 0\n" +
                "  },\n" +
                "  {\n" +
                "    \"code\": \"R9\",\n" +
                "    \"name\": \"سوس - ماسة\",\n" +
                "    \"hierarchyLevel\": 1,\n" +
                "    \"hierarchyName\": \"Region\",\n" +
                "    \"parentLocCode\": \"MOR\",\n" +
                "    \"langCode\": \"ara\",\n" +
                "    \"isActive\": true,\n" +
                "    \"kzgUrban\": 0,\n" +
                "    \"kzgRural\": 0.221\n" +
                "  }\n" +
                "\n" +
                "\n" +
                "]\n"
    }

    private fun updateUrbanRegion(selectedRegionName: Region) {
        urbanFamilyViewModel.updateFamilyRegion(selectedRegionName)
    }

    private fun updateRuralRegion(selectedRegionName: Region) {
        ruralFamilyViewModel.updateFamilyRegion(selectedRegionName)
    }

}