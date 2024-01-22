package com.example.calculatersunotes.data.models

class RuralHouse(
    override var bigGasBottlesInMonth: Double = 0.0,
    override var smallGasBottlesInMonth: Double = 0.0,
    override var electricityBillMonthOne: Double = 0.0,
    override var electricityBillMonthTwo: Double = 0.0,
    override var electricityBillMonthThree: Double = 0.0,
    override var waterBillMonthOne: Double = 0.0,
    override var waterBillMonthTwo: Double = 0.0,
    override var waterBillMonthThree: Double = 0.0,
    override var internetAndPhoneExpensesInMonth: Double = 0.0,
    var hasCar: Boolean = false,
    var isHouseTypeOne: Boolean = true,
    var hasMotorcycle: Boolean = false,
    var hasNoAgriculturalLand: Boolean = true,
    var hasIrrigatedLand: Boolean = false,
    var cowsNumber: UInt = 0u, override var roomsNumber: UInt = 0u

): House {

}