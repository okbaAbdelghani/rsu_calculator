package com.example.calculatersunotes.ui.edit.urban.member

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.calculatersunotes.R


private const val ARG_MEMBER_ID = "param1"

class EditUrbanMember : Fragment() {
    private var ARG_MEMBER_ID: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            ARG_MEMBER_ID = it.getString(ARG_MEMBER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_urban_member, container, false)
    }

    companion object {
        private const val ARG_MEMBER_ID = "member_id"

        fun newInstance(memberId: String) : EditUrbanMember {
            val fragment = EditUrbanMember()
            val args = Bundle()
            args.putString(ARG_MEMBER_ID, memberId)
            fragment.arguments = args
            return fragment
        }
    }
}