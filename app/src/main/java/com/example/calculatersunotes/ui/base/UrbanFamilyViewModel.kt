package com.example.calculatersunotes.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.Region
import com.example.calculatersunotes.data.models.UrbanFamily
import com.example.calculatersunotes.data.models.UrbanHouse
import com.example.calculatersunotes.data.models.UrbanHouseHolder

class UrbanFamilyViewModel : ViewModel() {
    private var _family = MutableLiveData<UrbanFamily>()
    val family : LiveData<UrbanFamily> = _family

    private var _result = MutableLiveData<Double>()
    val result : LiveData<Double> = _result

    fun updateFamilyHouseHolder(houseHolder: UrbanHouseHolder){
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.householder = houseHolder
        _family.value = currentFamily
    }

    fun updateSecondHousePossession(value: Boolean) {
        val urbanFamily = _family.value ?: createEmptyFamily()
        urbanFamily.hasSecondHouse = value
        _family.value = urbanFamily
    }

    fun updateFamilyHouse(urbanHouse : UrbanHouse) {
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.urbanHouse = urbanHouse
        _family.value = currentFamily
    }

    private fun createEmptyFamily(): UrbanFamily {
        return UrbanFamily(false, Region(), mutableListOf(), UrbanHouseHolder(), mutableListOf(),UrbanHouse())
    }


}