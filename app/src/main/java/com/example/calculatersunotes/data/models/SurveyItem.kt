package com.example.calculatersunotes.data.models

import kotlinx.serialization.Serializable

@Serializable
data class SurveyItem(
    var viQuestion: String = "",
    var vi: Double = 0.0,
    var ci: Double = 0.0,
    var type: String = "",
    var category: String ="",
    var isCalculated: Boolean = false
){
    override fun toString(): String {
        return "Question(viQuestion='$viQuestion', vi=$vi, ci=$ci, type='$type', category='$category', isCalculated=$isCalculated)"
    }

    fun getItemProduct(): Double{
        return vi*ci
    }
}


