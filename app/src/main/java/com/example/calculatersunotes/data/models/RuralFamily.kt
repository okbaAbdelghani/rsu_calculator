package com.example.calculatersunotes.data.models

class RuralFamily(
    override var hasSecondHouse: Boolean,
    override var region: Region,
    override var surveyItems: MutableList<SurveyItem>,
    override var environment: String,
    var householder: RuralHouseHolder,
    var members: MutableList<RuralMember>,

) : Family
{
    override fun calculateRSU(): Double {

        val kmr = 8.695

        var smu = 0.0
        for (question in surveyItems) {
            smu += question.getItemProduct()
        }
        smu += region.kzgRural + kmr
        return smu
    }
}