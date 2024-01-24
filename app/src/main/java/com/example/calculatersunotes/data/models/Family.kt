package com.example.calculatersunotes.data.models

import android.os.Environment


 interface Family {

     var hasSecondHouse: Boolean
     var region: Region
     var surveyItems: MutableList<SurveyItem>

     //Calculates The RSU for a family
     fun calculateRSU(): Double


 }



