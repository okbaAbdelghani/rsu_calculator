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
import com.example.calculatersunotes.databinding.FragmentEnvironmentsBinding
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel


class EnvironmentsFragment : Fragment() {
    private var _binding : FragmentEnvironmentsBinding? = null

    private var buttonList: List<Button> = mutableListOf()
    private lateinit var fragmentUtil: FragmentUtil
    private var selectedEnv: String = ""
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEnvironmentsBinding.inflate(inflater, container, false)

        fragmentUtil = FragmentUtil(requireContext())

        buttonList = mutableListOf(
            binding.ruralBtn,
            binding.urbanBtn
        )

        environmentViewModel.environment.observe(viewLifecycleOwner) {
            val isNull : Boolean = it.isNullOrEmpty()

            if(isNull) {
                fragmentUtil.setInactiveButtonColors(buttonList, null)
            } else {
                when(it) {
                    "urban" -> fragmentUtil.setInactiveButtonColors(buttonList, binding.urbanBtn)
                    "rural" -> fragmentUtil.setInactiveButtonColors(buttonList, binding.ruralBtn)
                }
            }
        }


        binding.ruralBtn.setOnClickListener {
            selectedEnv = "rural"
            setEnvironment(selectedEnv)
            fragmentUtil.setInactiveButtonColors(buttonList, binding.ruralBtn)
        }

        binding.urbanBtn.setOnClickListener {
            selectedEnv = "urban"
            setEnvironment(selectedEnv)
            fragmentUtil.setInactiveButtonColors(buttonList, binding.urbanBtn)
        }

        return binding.root
    }

    private fun setEnvironment(value: String) {
        environmentViewModel.updateEnvironment(value)
    }

}