package com.example.calculatersunotes.ui.urban.house

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculatersunotes.data.models.UrbanHouse

class UrbanHouseViewModel: ViewModel() {
    private var _urbanHouse = MutableLiveData<UrbanHouse>()
    val urbanHouse : LiveData<UrbanHouse> = _urbanHouse

    fun updateRoomsNumber(value: UInt){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.roomsNumber = value
        _urbanHouse.value = urbanHouse
    }

    fun updatePhonesNumber(value: UInt) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.numberOfPhones = value
        _urbanHouse.value = urbanHouse
    }

    fun updateElectricityMeterPossession(value: Boolean){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasElectricityMeter = value
        _urbanHouse.value = urbanHouse
    }

    fun updateMotorcycleNumber(value: UInt) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.numberOfMotorcycles = value
        _urbanHouse.value = urbanHouse
    }

    fun updateCarsNumber(value: UInt) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.numberOfCars = value
        _urbanHouse.value = urbanHouse
    }

    fun updateShowerPossession(value: Boolean){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasShower = value
        _urbanHouse.value = urbanHouse
    }

    fun updateAntennaPossession(value: Boolean) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasAntenna = value
        _urbanHouse.value = urbanHouse
    }

    fun updateFixPossession(value: Boolean) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasFixPhone = value
        _urbanHouse.value = urbanHouse
    }

    fun updateLoanPossession(value: Boolean) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasBankLoan = value
        _urbanHouse.value = urbanHouse
    }


    fun updateGaragePossession(value: Boolean) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasGarage = value
        _urbanHouse.value = urbanHouse
    }

    fun updateShopsPossession(value: Boolean) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasProfessionalShops = value
        _urbanHouse.value = urbanHouse
    }

    fun updateComputerPossession(value: Boolean) {
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.hasLaptop = value
        _urbanHouse.value = urbanHouse
    }

    private fun createEmptyHouse(): UrbanHouse {
        return UrbanHouse()
    }

    fun updateBigGasBottlesInMonth(value: UInt){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.bigGasBottlesInMonth = (value*40u).toDouble()
        _urbanHouse.value = urbanHouse
    }

    fun updateSmallGasBottlesInMonth(value: UInt){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.smallGasBottlesInMonth = (value*20u).toDouble()
        _urbanHouse.value = urbanHouse
    }

    fun updateInternetAndPhoneMonthlyBill(value: Double){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.internetAndPhoneExpensesInMonth = value
        _urbanHouse.value = urbanHouse
    }

    fun updateElectricityMonthOne(value: Double){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.electricityBillMonthOne = value
        _urbanHouse.value = urbanHouse
    }

    fun updateElectricityMonthTwo(value: Double){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.electricityBillMonthTwo = value
        _urbanHouse.value = urbanHouse
    }

    fun updateElectricityMonthThree(value: Double){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.electricityBillMonthThree = value
        _urbanHouse.value = urbanHouse
    }

    fun updateWaterMonthFirst(value: Double){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.waterBillMonthOne = value
        _urbanHouse.value = urbanHouse
    }

    fun updateWaterMonthSecond(value: Double){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.waterBillMonthTwo = value
        _urbanHouse.value = urbanHouse
    }

    fun updateWaterMonthThree(value: Double){
        val urbanHouse = _urbanHouse.value ?: createEmptyHouse()
        urbanHouse.waterBillMonthThree = value
        _urbanHouse.value = urbanHouse
    }


}