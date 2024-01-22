package com.example.calculatersunotes.ui.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.calculatersunotes.R

class CustomSpinnerAdapter(context: Context, resource: Int, data: List<String>) :
    ArrayAdapter<String>(context, resource, data){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return super.getView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val textView = super.getDropDownView(position, convertView, parent) as TextView
        return textView
    }

    private fun createItemView(position: Int, convertView: View?,parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?:  inflater.inflate(R.layout.region_spinner_item, parent, false)

        val texView = view.findViewById<TextView>(R.id.spinnerItemText)
        texView.text = getItem(position)

        return view
    }
}