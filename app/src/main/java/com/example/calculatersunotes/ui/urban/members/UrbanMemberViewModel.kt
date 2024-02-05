package com.example.calculatersunotes.ui.urban.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.UrbanMember

class UrbanMemberViewModel : ViewModel() {
    private var _urbanMember = MutableLiveData<UrbanMember>()
    val urbanMember : LiveData<UrbanMember> = _urbanMember

    fun updateMemberAge(age: UShort) {
        val currentMember = _urbanMember.value ?: createEmptyMember()
        currentMember.age = age
        _urbanMember.value = currentMember
    }

    fun updateMemberName(name: String) {
        val currentMember = _urbanMember.value ?: createEmptyMember()
        currentMember.fullName = name
        _urbanMember.value = currentMember
    }

    fun updateHealthSecurityPossession(value: Boolean) {
        val currentMember = _urbanMember.value ?: createEmptyMember()
        currentMember.hasHealthCoverage = value
        _urbanMember.value = currentMember
    }

    fun updateSchooling(value: Boolean) {
        val currentMember = _urbanMember.value ?: createEmptyMember()
        currentMember.isSchooler = value
        _urbanMember.value = currentMember
    }


    fun updateDiplomaPossession(value: Boolean) {
        val currentMember = _urbanMember.value ?: createEmptyMember()
        currentMember.hasDiploma = value
        _urbanMember.value = currentMember
    }

    fun updateSchoolType(value: String) {
        val currentMember = _urbanMember.value ?: createEmptyMember()

        if(value == "private") {
            currentMember.privateSector = true
            currentMember.publicSector = false
        }

        if(value == "public") {
            currentMember.privateSector = false
            currentMember.publicSector = true
        }

        _urbanMember.value = currentMember
    }

    fun updateRelationship(value: String) {
        val currentMember = _urbanMember.value ?: createEmptyMember()

        if(value == "child") {
            currentMember.isChildOfHouseHolder = true
            currentMember.isPartnerOfHouseHolder = false
        }

        if(value == "partner") {
            currentMember.isChildOfHouseHolder = true
            currentMember.isPartnerOfHouseHolder = false
        }

        if(value == "other") {
            currentMember.isChildOfHouseHolder = false
            currentMember.isPartnerOfHouseHolder = false
        }

        _urbanMember.value = currentMember
    }

    fun updateWorkStatus(value: Boolean) {
        val currentMember = _urbanMember.value ?: createEmptyMember()
        currentMember.hasAJob = value
        _urbanMember.value = currentMember
    }

    fun updatePositionStatus(value: String) {
        val currentMember = _urbanMember.value ?: createEmptyMember()

        if(value == "high_position") {
            currentMember.hasHighProfessionalPosition = true
            currentMember.equipmentManagementActivity = false
        }

        if(value == "machinery") {
            currentMember.hasHighProfessionalPosition = false
            currentMember.equipmentManagementActivity = true
        }

        if(value == "other") {
            currentMember.hasHighProfessionalPosition = false
            currentMember.equipmentManagementActivity = false
        }
        _urbanMember.value = currentMember
    }


    private fun createEmptyMember(): UrbanMember {
        return UrbanMember()
    }

}