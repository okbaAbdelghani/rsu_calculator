package com.example.calculatersunotes.ui.edit

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.base.EnvironmentViewModel
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel
import com.example.calculatersunotes.ui.edit.rural.house.EditRuralHouse
import com.example.calculatersunotes.ui.edit.rural.householder.EditRuralHouseholder
import com.example.calculatersunotes.ui.edit.urban.householder.EditUrbanHouseHolder
import com.example.calculatersunotes.ui.result.ResultFragment
import com.example.calculatersunotes.utils.FragmentUtil

class Edit : Fragment() {
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()
    private lateinit var fragmentUtil: FragmentUtil
    private val environmentViewModel: EnvironmentViewModel by activityViewModels()
    private var selectedEnv = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit, container, false)

        environmentViewModel.environment.observe(viewLifecycleOwner, Observer {env ->
            selectedEnv = env

            when (env) {
                "urban" -> {

                }
                "rural" -> {

                }
            }
        })

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.members_recycler)
        val editMembersBtn = rootView.findViewById<FrameLayout>(R.id.members_part_btn)
        val membersCardView = rootView.findViewById<CardView>(R.id.members_card_view)
        val textView = rootView.findViewById<TextView>(R.id.members_part_txt)
        val backBtn = rootView.findViewById<ImageButton>(R.id.back_button)
        val recalculateBtn = rootView.findViewById<Button>(R.id.recalculate_btn)

        val addMemberBtn = rootView.findViewById<Button>(R.id.add_member_btn)

        val emptyStateView = rootView.findViewById<LinearLayout>(R.id.empty_state_view)
        emptyStateView.visibility = View.GONE

        val householderBtn = rootView.findViewById<FrameLayout>(R.id.your_own_part_btn)
        val homePartBtn = rootView.findViewById<FrameLayout>(R.id.home_part_btn)

        fragmentUtil = FragmentUtil(requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val members = family.members
            val adapter = MemberAdapter(members)
            if(members.isEmpty()) {
                emptyStateView.visibility = View.VISIBLE
            } else {
                emptyStateView.visibility = View.GONE
            }

            recyclerView.adapter = adapter
        }

        membersCardView.visibility = View.GONE

        editMembersBtn.setOnClickListener {
            toggleViewVisibilityWithAnimation(membersCardView, textView, editMembersBtn)
        }

        householderBtn.setOnClickListener {
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

        homePartBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, EditRuralHouse())
        }


        backBtn.setOnClickListener {
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, ResultFragment())
        }

        recalculateBtn.setOnClickListener {
            recalculateBtn.setTextColor(Color.WHITE)
            fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, ResultFragment())
        }

        addMemberBtn.setOnClickListener {
            addMemberBtn.setTextColor(Color.WHITE)
        }



        return rootView
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



}