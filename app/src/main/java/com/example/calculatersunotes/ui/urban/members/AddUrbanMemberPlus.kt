package com.example.calculatersunotes.ui.urban.members

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.calculatersunotes.R
import com.example.calculatersunotes.utils.FragmentUtil

class AddUrbanMemberPlus : Fragment() {
    private lateinit var fragmentUtil: FragmentUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentUtil = FragmentUtil(requireContext())
        val rootView = inflater.inflate(R.layout.fragment_add_urban_member_plus, container, false)
        val nextBtn = rootView.findViewById<ImageButton>(R.id.back_button)

        nextBtn.setOnClickListener{
            navigateToAddMember()
        }

        return rootView
    }

    private fun navigateToAddMember() {
        fragmentUtil.replaceFragment(requireActivity().supportFragmentManager,R.id.fragmentContainer, AddUrbanMember())
    }

}