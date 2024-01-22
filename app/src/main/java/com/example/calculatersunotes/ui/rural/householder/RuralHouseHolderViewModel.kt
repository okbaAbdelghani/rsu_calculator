package com.example.calculatersunotes.ui.rural.householder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.RuralHouseHolder

class RuralHouseHolderViewModel: ViewModel() {
    private var _ruralHouseHolder = MutableLiveData<RuralHouseHolder>()
    val ruralHouseHolder : LiveData<RuralHouseHolder> = _ruralHouseHolder


    fun updateName(name: String) {
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.fullName = name
        _ruralHouseHolder.value = currentHouseHolder
    }

    fun updateAge(age: UShort) {
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.age = age
        _ruralHouseHolder.value = currentHouseHolder
    }

    fun updateEducationLevel(value: Boolean) {
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.hasBasicEducation = value
        _ruralHouseHolder.value = currentHouseHolder
    }

    private fun createEmptyHouseHolder(): RuralHouseHolder {
        return RuralHouseHolder()
    }

    fun updateSocialStatus(job: String){
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        if(job == "machinery_manager"){
            currentHouseHolder.fixEquipmentOrMachinery = true
            currentHouseHolder.isCraftsman = false
            currentHouseHolder.isFarmer = false
            currentHouseHolder.hasHighProfessionalPosition = false
        }
        if(job == "craftsman"){
            currentHouseHolder.fixEquipmentOrMachinery = false
            currentHouseHolder.isCraftsman = true
            currentHouseHolder.isFarmer = false
            currentHouseHolder.hasHighProfessionalPosition = false
        }
        if(job == "farmer"){
            currentHouseHolder.fixEquipmentOrMachinery = false
            currentHouseHolder.isCraftsman = false
            currentHouseHolder.isFarmer = true
            currentHouseHolder.hasHighProfessionalPosition = false
        }
        if(job == "high_position"){
            currentHouseHolder.fixEquipmentOrMachinery = false
            currentHouseHolder.isCraftsman = false
            currentHouseHolder.isFarmer = false
            currentHouseHolder.hasHighProfessionalPosition = true
        }
        if(job == "none"){
            currentHouseHolder.fixEquipmentOrMachinery = false
            currentHouseHolder.isCraftsman = false
            currentHouseHolder.isFarmer = false
            currentHouseHolder.hasHighProfessionalPosition = false
        }
        _ruralHouseHolder.value = currentHouseHolder
    }

    fun updateJobPosition(status: String){
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        if(status == "employer"){
            currentHouseHolder.isRecruiting = true

        }
        if(status == "without"){
            currentHouseHolder.isRecruiting = false
        }
        _ruralHouseHolder.value = currentHouseHolder
    }

    fun isMerchant(value: Boolean){
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.isMerchant = value
        _ruralHouseHolder.value = currentHouseHolder
    }

    fun hasHealthCoverage(value: Boolean){
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.hasHealthCoverage = value
        _ruralHouseHolder.value = currentHouseHolder
    }

    fun setLiteracy(value: Boolean){
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.isLiterate = value
        _ruralHouseHolder.value = currentHouseHolder
    }

    fun hasHighEducationDiploma(value: Boolean){
        val currentHouseHolder = _ruralHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.hasHighEducationDiploma = value
        _ruralHouseHolder.value = currentHouseHolder
    }

}