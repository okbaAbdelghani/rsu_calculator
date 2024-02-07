package com.example.calculatersunotes.ui.base

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.Region
import com.example.calculatersunotes.data.models.SurveyItem
import com.example.calculatersunotes.data.models.UrbanFamily
import com.example.calculatersunotes.data.models.UrbanHouse
import com.example.calculatersunotes.data.models.UrbanHouseHolder
import com.example.calculatersunotes.data.models.UrbanMember
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import kotlin.math.pow

class UrbanFamilyViewModel : ViewModel() {
    private var _family = MutableLiveData<UrbanFamily>()
    val family: LiveData<UrbanFamily> = _family

    private var _result = MutableLiveData<Double>()
    val result: LiveData<Double> = _result

    fun updateFamilyHouseHolder(houseHolder: UrbanHouseHolder) {
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.householder = houseHolder
        _family.value = currentFamily
    }

    fun updateFamilyRegion(region: Region) {
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.region = region
        _family.value = currentFamily
    }

    fun updateSecondHousePossession(value: Boolean) {
        val urbanFamily = _family.value ?: createEmptyFamily()
        urbanFamily.hasSecondHouse = value
        _family.value = urbanFamily
    }

    fun addFamilyMember(member: UrbanMember) {
        val urbanFamily = _family.value ?: createEmptyFamily()
        urbanFamily.members.add(member)
        _family.value = urbanFamily
    }



    fun updateFamilyHouse(urbanHouse: UrbanHouse) {
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.urbanHouse = urbanHouse
        _family.value = currentFamily
    }

    private fun createEmptyFamily(): UrbanFamily {
        return UrbanFamily(
            false,
            Region(),
            mutableListOf(),
            UrbanHouseHolder(),
            mutableListOf(),
            UrbanHouse()
        )
    }

    fun createSurveyItems(context: Context) {
        val currentFamily = _family.value ?: createEmptyFamily()

        val surveyVis = mutableListOf<Double>().apply {
            add(getV1(currentFamily))
            add(getV2(currentFamily))
            add(getV3(currentFamily))
            add(getV4(currentFamily))
            add(getV5(currentFamily))
            add(getV6(currentFamily))
            add(getV7(currentFamily))
            add(getV8(currentFamily))
            add(getV9(currentFamily))
            add(getV10(currentFamily))
            add(getV11(currentFamily))
            add(getV12(currentFamily))
            add(getV13(currentFamily))
            add(getV14(currentFamily))
            add(getV15(currentFamily))
            add(getV16(currentFamily))
            add(getV17(currentFamily))
            add(getV18(currentFamily))
            add(currentFamily.members.size + 1.0)
            add((currentFamily.members.size.toDouble() + 1.0).pow(2))
            add(currentFamily.householder.age.toDouble())
            add((currentFamily.householder.age.toDouble()).pow(2))
            add(getV23(currentFamily))
            add(getV24(currentFamily))
            add(getV25(currentFamily))
            add(getV26(currentFamily))
            add(getV27(currentFamily))
            add(getV28(currentFamily))
            add(getV29(currentFamily))
            add(currentFamily.urbanHouse.numberOfPhones.toDouble())
            add(currentFamily.urbanHouse.numberOfCars.toDouble())
            add(currentFamily.urbanHouse.numberOfMotorcycles.toDouble())
            add(getV33(currentFamily))
            add(getV34(currentFamily))
            add(getV35(currentFamily))
        }

        var surveyItems = fetchQuestions(context)
        for ((i, item) in surveyItems.withIndex()){
            item.vi = surveyVis[i]
        }
        println(surveyItems.toString())

        _result.value = _family.value?.calculateRSU()
        _family.value = currentFamily
    }

    private fun fetchQuestions(context: Context): MutableList<SurveyItem> {

        val stringResult = readJsonFromAssets("json/urbanQuestions.json", context)
        val result = parseJsonToModel(stringResult)

        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.surveyItems = result
        _family.value = currentFamily

        return result

    }

    private fun getV1(family: UrbanFamily): Double {

        return if (family.members.size == 0) {
            1.0
        } else if (family.members.size == 1) {
            if (family.members[0].isPartnerOfHouseHolder) {
                1.0
            } else {
                0.0
            }
        } else {
            0.0
        }

    }

    private fun getV2(family: UrbanFamily): Double {
        var result = 0.0
        for (member in family.members) {
            if (!member.isPartnerOfHouseHolder && !member.isChildOfHouseHolder) {
                result = 1.0
                break
            }
        }
        return result

    }

    private fun getV3(family: UrbanFamily): Double {
        return if ((family.members.size + 1).toUInt()/(family.urbanHouse.roomsNumber)  <= 2u) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV4(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasShower) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV5(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasElectricityMeter) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV6(family: UrbanFamily): Double {
        return if (family.householder.hasHighEducationLevel) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV7(family: UrbanFamily): Double {
        return if (family.householder.hasHighProfessionalPosition) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV8(family: UrbanFamily): Double {
        return if (family.householder.hasAverageProfessionalPosition) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV9(family: UrbanFamily): Double {
        return if (family.householder.isRecruiting) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV10(family: UrbanFamily): Double {
        return if (family.householder.isRetiredOrBeneficiaryOfIncome) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV11(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasLaptop) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV12(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasAntenna) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV13(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasFixPhone) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV14(family: UrbanFamily): Double {
        var ret = 0.0;
        if (family.householder.hasHealthCoverage) {
            ret = 1.0
        } else {
            if (family.members.size > 0) {
                for (member in family.members) {
                    if (member.hasHealthCoverage) {
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

    private fun getV15(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasBankLoan) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV16(family: UrbanFamily): Double {
        return if (family.hasSecondHouse) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV17(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasGarage) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV18(family: UrbanFamily): Double {
        return if (family.urbanHouse.hasProfessionalShops) {
            1.0
        } else {
            0.0
        }
    }

    private fun getV23(family: UrbanFamily): Double {
        var number = 0.0
        for (member in family.members) {
            if (member.age >= 6u && member.age <= 14u && member.privateSector) {
                number++
            }
        }

        return number
    }

    private fun getV24(family: UrbanFamily): Double {
        var number = 0.0
        for (member in family.members) {
            if (member.age >= 6u && member.age <= 14u && member.publicSector) {
                number++
            }
        }

        return number
    }

    private fun getV25(family: UrbanFamily): Double {
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var noDiplomaMembers = 0

        for (member in family.members) {
            if (member.age >= 15u && !member.hasDiploma) {
                noDiplomaMembers++
            }
        }

        if (family.householder.age >= 15u && !family.householder.hasDiploma) {
            noDiplomaMembers++
        }

        rate = noDiplomaMembers.toDouble() / totalMembers
        return rate
    }

    private fun getV26(family: UrbanFamily): Double {
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var DiplomaMembers = 0

        for (member in family.members) {
            if (member.age >= 15u && member.hasDiploma) {
                DiplomaMembers++
            }
        }

        if (family.householder.age >= 15u && family.householder.hasDiploma) {
            DiplomaMembers++
        }

        rate = DiplomaMembers.toDouble() / totalMembers
        return rate
    }

    private fun getV27(family: UrbanFamily): Double {
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var jobMembers = 0

        for (member in family.members) {
            if (member.age >= 15u && member.hasAJob) {
                jobMembers++
            }
        }

        if (family.householder.age >= 15u && (family.householder.hasAJob || family.householder.hasHighProfessionalPosition || family.householder.hasAverageProfessionalPosition)) {
            jobMembers++
        }

        rate = jobMembers.toDouble() / totalMembers
        return rate
    }

    private fun getV28(family: UrbanFamily): Double {
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var diplomaMembers = 0

        for (member in family.members) {
            if (member.age >= 15u && member.equipmentManagementActivity) {
                diplomaMembers++
            }
        }

        if (family.householder.age >= 15u && family.householder.equipmentManagementActivity) {
            diplomaMembers++
        }

        rate = diplomaMembers.toDouble() / totalMembers
        return rate
    }

    private fun getV29(family: UrbanFamily): Double {
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var diplomaMembers = 0

        for (member in family.members) {
            if ( member.hasHighProfessionalPosition) {
                diplomaMembers++
            }
        }

        if ( family.householder.hasHighProfessionalPosition) {
            diplomaMembers++
        }

        rate = diplomaMembers.toDouble() / totalMembers
        return rate
    }

    private fun getV33(family: UrbanFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1
        var gasCostByMonth = family.urbanHouse.bigGasBottlesInMonth + family.urbanHouse.smallGasBottlesInMonth

        rate = (gasCostByMonth*12.0)/totalMembers
        return rate
    }

    private fun getV34(family: UrbanFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1
        val waterCostAvg = (family.urbanHouse.waterBillMonthOne + family.urbanHouse.waterBillMonthTwo + family.urbanHouse.waterBillMonthThree) /3
        val electricityCostAvg = (family.urbanHouse.electricityBillMonthOne + family.urbanHouse.electricityBillMonthTwo + family.urbanHouse.electricityBillMonthThree) /3

        rate = ((waterCostAvg + electricityCostAvg)*12.0)/totalMembers
        return rate
    }

    private fun getV35(family: UrbanFamily): Double{
        var rate = 0.0
        val totalMembers = family.members.size + 1

        rate = (family.urbanHouse.internetAndPhoneExpensesInMonth*12.0)/totalMembers
        return rate
    }


    private fun readJsonFromAssets(fileName: String, context: Context): String? {
        return try {
            context.assets.open(fileName).use { inputStream ->
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                String(buffer)
            }
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