package com.example.calculatersunotes.ui.rural.members

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.RuralMember

class RuralMemberViewModel : ViewModel() {
    private var _ruralMember = MutableLiveData<RuralMember>()
    val ruralMember : LiveData<RuralMember> = _ruralMember

    fun updateMemberAge(age: UShort) {
        val currentMember = _ruralMember.value ?: createEmptyMember()
        currentMember.age = age
        _ruralMember.value = currentMember
    }

    fun updateMemberName(name: String) {
        val currentMember = _ruralMember.value ?: createEmptyMember()
        currentMember.fullName = name
        _ruralMember.value = currentMember
    }

    fun updateHealthSecurityPossession(value: Boolean) {
        val currentMember = _ruralMember.value ?: createEmptyMember()
        currentMember.hasHealthCoverage = value
        _ruralMember.value = currentMember
    }

    fun updateLiteracyStatus(value: Boolean) {
        val currentMember = _ruralMember.value ?: createEmptyMember()
        currentMember.isLiterate = value
        _ruralMember.value = currentMember
    }

    fun updateHighEducationDiploma(value: Boolean) {
        val currentMember = _ruralMember.value ?: createEmptyMember()
        currentMember.hasHighEducationDiploma = value
        _ruralMember.value = currentMember
    }

    fun isMerchant(value: Boolean) {
        val currentMember = _ruralMember.value ?: createEmptyMember()
        currentMember.isMerchant = value
        _ruralMember.value = currentMember
    }

    private fun createEmptyMember(): RuralMember {
        return RuralMember("")
    }

}