package com.example.calculatersunotes.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.Family
import com.example.calculatersunotes.data.models.HouseHolder
import com.example.calculatersunotes.data.models.Member
import com.example.calculatersunotes.data.models.Region
import com.example.calculatersunotes.data.models.RuralFamily
import com.example.calculatersunotes.data.models.RuralHouseHolder
import com.example.calculatersunotes.data.models.UrbanHouseHolder

class RuralFamilyViewModel: ViewModel() {

    private var _family = MutableLiveData<RuralFamily>()
    val family : LiveData<RuralFamily> = _family


    fun setCurrentFamily(family: RuralFamily) {
        _family.value = family
    }

    fun getCurrentFamily(): LiveData<RuralFamily>  {
        return family
    }

    fun updateFamilyEnvironment(value: String){
        val currentFamily = _family.value ?: createEmptyFamily()
        currentFamily.environment = value
        _family.value = currentFamily
    }

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

    private fun createEmptyFamily(): RuralFamily {
        return RuralFamily(false,Region(), mutableListOf(),"rural", RuralHouseHolder(), mutableListOf())
    }

}