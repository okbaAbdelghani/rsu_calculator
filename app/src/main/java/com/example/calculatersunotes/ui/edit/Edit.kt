package com.example.calculatersunotes.ui.edit

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatersunotes.R
import com.example.calculatersunotes.databinding.FragmentEditBinding
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.base.UrbanFamilyViewModel
import com.example.calculatersunotes.ui.edit.rural.house.EditRuralHouse
import com.example.calculatersunotes.ui.edit.rural.householder.EditRuralHouseholder
import com.example.calculatersunotes.ui.edit.urban.householder.EditUrbanHouseHolder
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.ui.rural.members.AddRuralMember
import com.example.calculatersunotes.ui.urban.members.AddUrbanMember
import com.example.calculatersunotes.utils.FragmentUtil

class Edit : Fragment() {
    private var _binding : FragmentEditBinding? = null

    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private val urbanFamilyViewModel: UrbanFamilyViewModel by activityViewModels()
    private lateinit var fragmentUtil: FragmentUtil
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
    private var selectedEnv = ""

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)

        binding.emptyStateView.visibility = View.GONE

        fragmentUtil = FragmentUtil(requireContext())

        binding.membersRecycler.layoutManager = LinearLayoutManager(requireContext())

        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            selectedEnv = env
            if(env == "urban") {
                listenToUrbanMembers(binding.emptyStateView, binding.membersRecycler)
            }

            if(env == "rural") {
                listenToRuralMembers(binding.emptyStateView, binding.membersRecycler)
            }
        })


        binding.membersCardView.visibility = View.GONE

        binding.membersPartBtn.setOnClickListener {
            toggleViewVisibilityWithAnimation(binding.membersCardView, binding.membersPartTxt, binding.membersPartBtn)
        }

        binding.yourOwnPartBtn.setOnClickListener {
            if (selectedEnv == "urban") {
                fragmentUtil.replaceFragment(
                    requireActivity().supportFragmentManager,
                    R.id.fragmentContainer,
                    EditUrbanHouseHolder()
                )
            }
            if (selectedEnv == "rural") {
                fragmentUtil.replaceFragment(
                    requireActivity().supportFragmentManager,
                    R.id.fragmentContainer,
                    EditRuralHouseholder()
                )
            }
        }

        binding.homePartBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, EditRuralHouse())
        }


        binding.backButton.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, ResultFragment())
        }

        binding.recalculateBtn.setOnClickListener {
            binding.recalculateBtn.setTextColor(Color.WHITE)
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, ResultFragment())
        }

        binding.addMemberBtn.setOnClickListener {
            if(selectedEnv == "urban") {
                fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMember())
            }

            if(selectedEnv == "rural") {
                fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddRuralMember())
            }
        }



        return binding.root
    }

    private fun toggleViewVisibilityWithAnimation(view: View, textView: TextView, btn: View) {
        val animationDuration = 300L
        val emptyBackground = ContextCompat.getDrawable(requireContext(), R.drawable.empty_btn_background)
        val fullBackground = ContextCompat.getDrawable(requireContext(), R.drawable.full_btn_background)

        if (view.visibility == View.VISIBLE) {
            val fadeOut = AlphaAnimation(1.0f, 0.0f)
            fadeOut.duration = animationDuration
            view.startAnimation(fadeOut)
            fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation) {}

                override fun onAnimationEnd(animation: android.view.animation.Animation) {
                    view.visibility = View.GONE
                }

                override fun onAnimationRepeat(animation: android.view.animation.Animation) {}
            })

            btn.background = emptyBackground
            textView.setTextColor(Color. BLACK)
        } else {
            val fadeIn = AlphaAnimation(0.0f, 1.0f)
            fadeIn.duration = animationDuration
            view.startAnimation(fadeIn)
            fadeIn.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation) {
                    view.visibility = View.VISIBLE
                }

                override fun onAnimationEnd(animation: android.view.animation.Animation) {}

                override fun onAnimationRepeat(animation: android.view.animation.Animation) {}
            })

            btn.background = fullBackground
            textView.setTextColor(Color.WHITE)
        }
    }

    private fun listenToRuralMembers(emptyStateView: LinearLayout, recyclerView: RecyclerView) {
        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val members = family.members
            val adapter = RuralMemberAdapter(members)

            if(members.isEmpty()) {
                emptyStateView.visibility = View.VISIBLE
            } else {
                emptyStateView.visibility = View.GONE
            }

            recyclerView.adapter = adapter
        }
    }

    private fun listenToUrbanMembers(emptyStateView: LinearLayout, recyclerView: RecyclerView) {
        urbanFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val members = family.members
            val adapter = UrbanMemberAdapter(members)

            if(members.isEmpty()) {
                emptyStateView.visibility = View.VISIBLE
            } else {
                emptyStateView.visibility = View.GONE
            }

            recyclerView.adapter = adapter
        }
    }



}