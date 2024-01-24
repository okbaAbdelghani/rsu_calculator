package com.example.calculatersunotes.utils

import android.widget.Button
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

class SwipeUtil {
    fun navigateNext(viewPager:  ViewPager?, pagerAdapter: PagerAdapter, navFunc: () -> Unit){
        if(viewPager?.currentItem == pagerAdapter.count - 1){
            navFunc()
        }
        if (viewPager?.currentItem!! < pagerAdapter.count){
            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
    }

}