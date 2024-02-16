package com.example.calculatersunotes.ui.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.calculatersunotes.R
import com.example.calculatersunotes.ui.intro.IntroFragment
import com.example.calculatersunotes.utils.FragmentUtil

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentUtil: FragmentUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentUtil = FragmentUtil(this)
        fragmentUtil.replaceFragment(IntroFragment())

    }
}

