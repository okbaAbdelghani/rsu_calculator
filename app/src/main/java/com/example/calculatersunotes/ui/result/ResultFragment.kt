package com.example.calculatersunotes.ui.result

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentResultBinding
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.Edit
import com.example.calculatersunotes.ui.rural.house.RuralHouseFragment
import com.example.calculatersunotes.ui.rural.house.RuralHouseViewModel
import com.example.calculatersunotes.ui.urban.house.UrbanHouseViewModel
import com.example.calculatersunotes.utils.AnimationUtil
import com.example.calculatersunotes.utils.FragmentUtil

class ResultFragment : Fragment() {
    private var _binding : FragmentResultBinding? = null

    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private val ruralHouseViewModel: RuralHouseViewModel by activityViewModels()
    private val urbanHouseViewModel: UrbanHouseViewModel by activityViewModels()
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
    private lateinit var fragmentUtil: FragmentUtil
    private var selectedEnv = ""
    private lateinit var animationUtil: AnimationUtil
    var name = ""

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        fragmentUtil = FragmentUtil(requireContext())
        animationUtil = AnimationUtil(requireContext())

        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            selectedEnv = env

            when (env) {
                "urban" -> {
                    observeUrbanHouse()
                    observeUrbanFamilyResult(binding.root)
                }
                "rural" -> {
                    observeRuralHouse()
                    observeRuralFamilyResult(binding.root)
                }
            }
        })

        goBack()
        navigateToEdit()

        return binding.root
    }


    private fun goBack(){
        binding.backButton.setOnClickListener{
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, RuralHouseFragment(), "left")
        }
    }

    private fun navigateToEdit(){
        binding.editBtn.setOnClickListener{
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, Edit())
        }
    }

    private fun observeUrbanHouse() {
        urbanHouseViewModel.urbanHouse.observe(viewLifecycleOwner) {
            urbanFamilyViewModel.updateFamilyHouse(it)
            urbanFamilyViewModel.createSurveyItems(requireContext())
        }
    }

    private fun observeRuralHouse() {
        ruralHouseViewModel.ruralHouse.observe(viewLifecycleOwner) {
            ruralFamilyViewModel.updateFamilyHouse(it)
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
        val valueAnimator = ValueAnimator.ofFloat(0f, result.toFloat())
        valueAnimator.duration = 2000


        valueAnimator.addUpdateListener { animator ->
            val animatedValue = animator.animatedValue as Float
            binding.resultTxt.text = String.format("%.3f", animatedValue)
        }
        valueAnimator.start()

        valueAnimator.doOnEnd {
            when (selectedEnv) {
                "urban" -> name = urbanFamilyViewModel.family.value?.householder?.fullName!!
                "rural" -> name = ruralFamilyViewModel.family.value?.householder?.fullName!!
            }

            setResultAdditional(result)
            setResultDescription(result)
            binding.editBtn.isEnabled = true
        }
    }

    private fun getSubscriptionAmount(result: Double) : Int {
        val thresholds = listOf(
            9.3264284 to 144,
            9.5124369 to 176,
            9.743001 to 224,
            9.9903727 to 287,
            10.237316 to 355,
            10.431048 to 454,
            10.739952 to 611
        )

        val matchedThreshold = thresholds.find { (lower, upper) -> result >lower && result <= upper }

        return matchedThreshold?.second ?: 1164
    }

    private fun setResultDescription(result: Double) {
        if(result <= 9.743001) {
            binding.tvResultDescription.text = "${getText(R.string.congrats_for_success_first)} $name ${getText(R.string.congrats_for_success_second)}"
            binding.tvResultDescription.setTextColor(resources.getColor(R.color.mainColor))
            binding.tvResultDescription.visibility = View.VISIBLE

        } else {
            binding.tvResultDescription.text = getText(R.string.unfortunate_result)
            binding.tvResultDescription.setTextColor(resources.getColor(R.color.red_color))
            binding.tvResultDescription.visibility = View.VISIBLE
        }

        animationUtil.makeResulTextAnimation(binding.tvResultDescription)
    }

    private fun setResultAdditional(result: Double) {
        val subscriptionAmount = getSubscriptionAmount(result)

        if(subscriptionAmount != 0) {
            binding.tvResultAdditional.text = "${getText(R.string.you_can_have_amo)}: $subscriptionAmount"
            binding.tvResultAdditional.visibility = View.VISIBLE
        } else {
            binding.tvResultAdditional.visibility = View.GONE
        }

        animationUtil.makeResulTextAnimation(binding.tvResultAdditional)
    }
}