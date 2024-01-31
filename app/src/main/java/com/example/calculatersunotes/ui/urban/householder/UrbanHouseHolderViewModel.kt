package com.example.calculatersunotes.ui.urban.householder

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.calculatersunotes.data.models.UrbanHouseHolder

class UrbanHouseHolderViewModel : ViewModel() {
    private var _urbanHouseHolder = MutableLiveData<UrbanHouseHolder>()
    val urbanHouseHolder : LiveData<UrbanHouseHolder> = _urbanHouseHolder

    fun updateName(name: String) {
        val currentHouseHolder = _urbanHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.fullName = name
        _urbanHouseHolder.value = currentHouseHolder
    }

    fun updateAge(age: UShort) {
        val currentHouseHolder = _urbanHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.age = age
        _urbanHouseHolder.value = currentHouseHolder
    }

    fun updateEducationLevel(value: Boolean) {
        val currentHouseHolder = _urbanHouseHolder.value ?: createEmptyHouseHolder()
        currentHouseHolder.hasHighEducationLevel = value
        _urbanHouseHolder.value = currentHouseHolder
    }

    private fun createEmptyHouseHolder(): UrbanHouseHolder {
        return UrbanHouseHolder()
    }

    fun updateSocialStatus(job: String){
        val currentHouseHolder = _urbanHouseHolder.value ?: createEmptyHouseHolder()
        if(job == "high_position"){
            currentHouseHolder.highProfessionalPosition = true
            currentHouseHolder.hasAverageProfessionalPosition = false
        }

        if(job == "medium_position"){
            currentHouseHolder.highProfessionalPosition = false
            currentHouseHolder.hasAverageProfessionalPosition = true
        }

        if(job == "none"){
            currentHouseHolder.highProfessionalPosition = false
            currentHouseHolder.hasAverageProfessionalPosition = false
        }
        _urbanHouseHolder.value = currentHouseHolder
    }

    fun updateJobPosition(status: String){
        val currentHouseHolder = _urbanHouseHolder.value ?: createEmptyHouseHolder()
        if(status == "employer"){
            currentHouseHolder.isRecruiting = true
            currentHouseHolder.hasAJob = false

        }
        if(status == "employee"){
            currentHouseHolder.hasAJob = true

        }
        if(status == "without"){
            currentHouseHolder.isRecruiting = false
        }
        _urbanHouseHolder.value = currentHouseHolder
    }
}