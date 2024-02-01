package com.example.calculatersunotes.data.models

class UrbanFamily(
    override var hasSecondHouse: Boolean,
    override var region: Region,
    override var surveyItems: MutableList<SurveyItem>,
    var householder: UrbanHouseHolder,
    var members: MutableList<UrbanMember>,
    var urbanHouse: UrbanHouse


) : Family {
    override fun calculateRSU(): Double {
        val kmu = 9.825

        var smu = 0.0
        for (question in surveyItems) {
            smu += question.getItemProduct()
        }
        smu += region.kzgUrban + kmu
        return smu
    }

}