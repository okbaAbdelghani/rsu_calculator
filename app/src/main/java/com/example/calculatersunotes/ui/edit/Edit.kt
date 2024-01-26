package com.example.calculatersunotes.ui.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatersunotes.R
import com.example.calculatersunotes.data.models.RuralMember
import com.example.calculatersunotes.ui.base.RuralFamilyViewModel

class Edit : Fragment() {
    private val ruralFamilyViewModel: RuralFamilyViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_edit, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.members_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        ruralFamilyViewModel.family.observe(viewLifecycleOwner) { family ->
            val members = family.members
            val adapter = MemberAdapter(members)

            recyclerView.adapter = adapter
        }



        return rootView
    }

}