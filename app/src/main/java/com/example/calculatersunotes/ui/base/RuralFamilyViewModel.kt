package com.example.calculatersunotes.ui.base

import android.content.Context
import android.os.Environment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.Region
import com.example.calculatersunotes.data.models.RuralFamily
import com.example.calculatersunotes.data.models.RuralHouse
import com.example.calculatersunotes.data.models.RuralHouseHolder
import com.example.calculatersunotes.data.models.RuralMember
import com.example.calculatersunotes.data.models.SurveyItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import kotlin.math.pow

class RuralFamilyViewModel : ViewModel() {
    private var _family = MutableLiveData<RuralFamily>()
    val family : LiveData<RuralFamily> = _family

    private var _result = MutableLiveData<Double>()
    val result : LiveData<Double> = _result

    fun updateFamilyRegion(region: Region) {
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.region = region
        _family.value = currentFamily
    }


    fun updateFamilyHouseHolder(houseHolder: RuralHouseHolder){
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.householder = houseHolder
        _family.value = currentFamily
    }

    fun updateSecondHousePossession(value: Boolean){
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.hasSecondHouse = value
        _family.value = currentFamily
    }

    fun addFamilyMember(member: RuralMember) {
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.members.add(member)
        _family.value = currentFamily
    }

    private fun createEmptyFamily(): RuralFamily {
        return RuralFamily(false,Region(), mutableListOf(), RuralHouseHolder(), mutableListOf(),
            RuralHouse()
        )
    }

    fun updateFamilyHouse(ruralHouse : RuralHouse) {
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.ruralHouse = ruralHouse
        _family.value = currentFamily
    }
    fun createSurveyItems(context: Context){
        val currentFamily = _family.value ?: createEmptyFamily()

        val surveyVis = mutableListOf<Double>().apply {
            add(getV1(currentFamily))
            add(if (currentFamily.ruralHouse.isHouseTypeOne) 1.0 else 0.0)
            add(getV3(currentFamily))
            add(getV4(currentFamily))
            add(if (currentFamily.householder.hasBasicEducation) 1.0 else 0.0)
            add(if (currentFamily.householder.hasHighProfessionalPosition) 1.0 else 0.0)
            add(if (currentFamily.householder.fixEquipmentOrMachinery) 1.0 else 0.0)
            add(if (currentFamily.householder.isCraftsman) 1.0 else 0.0)
            add(if (currentFamily.householder.isFarmer) 1.0 else 0.0)
            add(if (currentFamily.householder.isRecruiting) 1.0 else 0.0)
            add(if (currentFamily.ruralHouse.hasCar) 1.0 else 0.0)
            add(if (currentFamily.ruralHouse.hasMotorcycle) 1.0 else 0.0)
            add(getV13(currentFamily))
            add(if (currentFamily.hasSecondHouse) 1.0 else 0.0)
            add(if (currentFamily.ruralHouse.hasNoAgriculturalLand) 1.0 else 0.0)
            add(if (currentFamily.ruralHouse.hasIrrigatedLand) 1.0 else 0.0)
            add(currentFamily.members.size + 1.0)
            add((currentFamily.members.size.toDouble() + 1.0).pow(2))
            add(getV19(currentFamily))
            add(getV20(currentFamily))
            add(getV21(currentFamily))
            add(getV22(currentFamily))
            add(getV23(currentFamily))
            add(getV24(currentFamily))
            add(currentFamily.ruralHouse.cowsNumber.toDouble())
            add(getV26(currentFamily))
            add(getV27(currentFamily))
            add(getV28(currentFamily))
        }

        var surveyItems = fetchQuestions(context)
        for ((i, item) in surveyItems.withIndex()){
            item.vi = surveyVis[i]
        }

        _result.value = _family.value?.calculateRSU()

        _family.value = currentFamily
    }

    private fun fetchQuestions(context: Context) : MutableList<SurveyItem> {

            val stringResult = readJsonFromAssets("json/ruralQuestions.json", context)
            val result = parseJsonToModel(stringResult)

            val currentFamily = _family.value ?: createEmptyFamily()
            currentFamily.surveyItems = result
            _family.value = currentFamily

            return result

    }

    private fun getV1(family: RuralFamily): Double{

        return if(family.members.size == 0){
            1.0
        } else if (family.members.size == 1) {
            if(family.members[0].isPartnerOfHouseHolder) {
                1.0
            } else {
                0.0
            }
        } else {
            0.0
        }

    }

    private fun getV3(family: RuralFamily): Double{
        return if(family.ruralHouse.roomsNumber == 1u && (family.members.size + 1) <= 2){
            1.0
        } else {
            0.0
        }
    }

    private fun getV4(family: RuralFamily): Double{
        return if(family.ruralHouse.roomsNumber == 1u && (family.members.size + 1) <= 3){
            1.0
        } else {
            0.0
        }
    }

    private fun getV13(family: RuralFamily): Double{
        var ret = 0.0;
        if(family.householder.hasHealthCoverage){
            ret = 1.0
        } else {
            if(family.members.size > 0){
                for (member in family.members) {
                    if (member.hasHealthCoverage){
                        ret = 1.0
                        break
                    }
                }
            } else {
                ret = 0.0
            }
        }
        return ret

    }

    private fun getV19(family: RuralFamily): Double{
       var sum = 0.0
        for (member in family.members) {
            if(member.age >= 19u && member.age <= 29u){
                sum++
            }
        }
        if(family.householder.age >= 19u &&  family.householder.age <= 29u) {
            sum ++
        }
        return sum
    }

    private fun getV20(family: RuralFamily): Double{
        var sum = 0.0
        for (member in family.members) {
            if(member.age >= 30u && member.age <= 59u){
                sum++
            }
        }
        if(family.householder.age >= 30u &&  family.householder.age <= 59u) {
            sum ++
        }
        return sum
    }

    private fun getV21(family: RuralFamily): Double{
        var sum = 0.0
        for (member in family.members) {
            if(member.age >= 60u){
                sum++
            }
        }
        if(family.householder.age >= 60u) {
            sum ++
        }
        return sum
    }

    private fun getV22(family: RuralFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var literateMembers = 0

        for (member in family.members) {
            if(member.age >= 15u && member.isLiterate){
                literateMembers ++
            }
        }

        if(family.householder.age >= 15u && family.householder.isLiterate){
            literateMembers ++
        }

        rate = literateMembers.toDouble()/totalMembers
        return rate
    }
    private fun getV23(family: RuralFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var highEduDiplomaMembers = 0

        for (member in family.members) {
            if(member.age >= 15u && member.hasHighEducationDiploma){
                highEduDiplomaMembers ++
            }
        }

        if(family.householder.age >= 15u && family.householder.hasHighEducationDiploma){
            highEduDiplomaMembers ++
        }

        rate = highEduDiplomaMembers.toDouble()/totalMembers
        return rate
    }

    private fun getV24(family: RuralFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var merchantMembers = 0

        for (member in family.members) {
            if(member.age >= 15u && member.isMerchant){
                merchantMembers ++
            }
        }

        if(family.householder.age >= 15u && family.householder.isMerchant){
            merchantMembers ++
        }

        rate = merchantMembers.toDouble()/totalMembers
        return rate
    }

    private fun getV26(family: RuralFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var gasCostByMonth = family.ruralHouse.bigGasBottlesInMonth + family.ruralHouse.smallGasBottlesInMonth
        rate = (gasCostByMonth*12.0)/totalMembers
        return rate
    }

    private fun getV27(family: RuralFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1
        val waterCostAvg = (family.ruralHouse.waterBillMonthOne + family.ruralHouse.waterBillMonthTwo + family.ruralHouse.waterBillMonthThree) /3
        val electricityCostAvg = (family.ruralHouse.electricityBillMonthOne + family.ruralHouse.electricityBillMonthTwo + family.ruralHouse.electricityBillMonthThree) /3


        rate = ((waterCostAvg + electricityCostAvg)*12.0)/totalMembers
        return rate
    }

    private fun getV28(family: RuralFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1

        rate = (family.ruralHouse.internetAndPhoneExpensesInMonth*12.0)/totalMembers
        return rate
    }

    private fun readJsonFromAssets(fileName: String, context: Context): String? {
        return try {
            context.assets.open(fileName).use { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                String(buffer)}
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun parseJsonToModel(jsonString: String?): MutableList<SurveyItem> {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<MutableList<SurveyItem>>() {}.type)
    }

}