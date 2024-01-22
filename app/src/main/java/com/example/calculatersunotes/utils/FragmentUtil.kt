package com.example.calculatersunotes.utils

import android.content.Context
import android.graphics.Color
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.calculatersunotes.R

class FragmentUtil(val context: Context){

    fun replaceFragment(
        fragmentManager: FragmentManager,
        containerId: Int,
        fragment: Fragment,
        addToBackStack: Boolean = false
    ) {
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }

     fun setInactiveButtonColors(buttonsList: List<Button>, exceptionButton: Button?){

        for (btn in buttonsList){
            if (btn === exceptionButton && exceptionButton != null) {
                setActiveButtonColor(btn)
            } else {
                setInactiveButtonColor(btn)
            }
        }
    }

    fun setDefaultActiveButtons(buttonsList: List<Button>, exceptionButtons: List<Button>){

        for (btn in buttonsList){
            if(btn in exceptionButtons){
                setActiveButtonColor(btn)
            } else {
                setInactiveButtonColor(btn)
            }

        }
    }

    private fun setActiveButtonColor(button: Button) {
        button.background = ContextCompat.getDrawable(context, R.drawable.full_btn_background)
        button.setTextColor(Color.WHITE)
    }

    private fun setInactiveButtonColor(button: Button) {
        button.background = ContextCompat.getDrawable(context, R.drawable.empty_btn_background)
        button.setTextColor(Color.BLACK)
    }

    fun toggleTwoOptions(activeButton: Button, inactiveButton: Button) {
        setActiveButtonColor(activeButton)
        setInactiveButtonColor(inactiveButton)
    }


}