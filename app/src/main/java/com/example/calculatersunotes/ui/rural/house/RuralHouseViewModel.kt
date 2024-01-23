package com.example.calculatersunotes.ui.rural.house

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.RuralHouse

class RuralHouseViewModel: ViewModel() {
    private var _ruralHouse = MutableLiveData<RuralHouse>()
    val ruralHouse : LiveData<RuralHouse> = _ruralHouse

    fun updateHouseType(value: Boolean){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.isHouseTypeOne = value
        _ruralHouse.value = ruralHouse
    }

    fun updateCarPossession(value: Boolean){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.hasCar = value
        _ruralHouse.value = ruralHouse
    }

    fun updateMotorcyclePossession(value: Boolean){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.hasMotorcycle = value
        _ruralHouse.value = ruralHouse
    }

    fun updateNonAgriculturalLandPossession(value: Boolean){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.hasNoAgriculturalLand = value
        _ruralHouse.value = ruralHouse
    }

    fun updateIrrigatedLandPossession(value: Boolean){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.hasIrrigatedLand = value
        _ruralHouse.value = ruralHouse
    }

    fun updateCowsNumber(value: UInt){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.cowsNumber = value
        _ruralHouse.value = ruralHouse
    }

    fun updateRoomsNumber(value: UInt){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.roomsNumber = value
        _ruralHouse.value = ruralHouse
    }

    fun updateBigGasBottlesInMonth(value: UInt){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.bigGasBottlesInMonth = (value*40u).toDouble()
        _ruralHouse.value = ruralHouse
    }

    fun updateSmallGasBottlesInMonth(value: UInt){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.smallGasBottlesInMonth = (value*20u).toDouble()
        _ruralHouse.value = ruralHouse
    }

    fun updateInternetAndPhoneMonthlyBill(value: Double){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.internetAndPhoneExpensesInMonth = value
        _ruralHouse.value = ruralHouse
    }

    fun updateElectricityMonthOne(value: Double){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.electricityBillMonthOne = value
        _ruralHouse.value = ruralHouse
    }

    fun updateElectricityMonthTwo(value: Double){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.electricityBillMonthTwo = value
        _ruralHouse.value = ruralHouse
    }

    fun updateElectricityMonthThree(value: Double){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.electricityBillMonthThree = value
        _ruralHouse.value = ruralHouse
    }

    fun updateWaterMonthFirst(value: Double){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.waterBillMonthOne = value
        _ruralHouse.value = ruralHouse
    }
    fun updateWaterMonthSecond(value: Double){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.waterBillMonthTwo = value
        _ruralHouse.value = ruralHouse
    }
    fun updateWaterMonthThree(value: Double){
        val ruralHouse = _ruralHouse.value ?: createEmptyHouse()
        ruralHouse.waterBillMonthThree = value
        _ruralHouse.value = ruralHouse
    }

    private fun createEmptyHouse(): RuralHouse {
        return RuralHouse()
    }
}