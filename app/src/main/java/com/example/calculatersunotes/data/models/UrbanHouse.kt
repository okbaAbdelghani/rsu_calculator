package com.example.calculatersunotes.data.models

class UrbanHouse(
    override var bigGasBottlesInMonth: UInt,
    override var smallGasBottlesInMonth: UInt,
    override var electricityBillMonthOne: Double,
    override var electricityBillMonthTwo: Double,
    override var electricityBillMonthThree: Double,
    override var waterBillMonthOne: Double,
    override var waterBillMonthTwo: Double,
    override var waterBillMonthThree: Double,
    override var internetAndPhoneExpensesInMonth: Double,
    var hasShower: Boolean,
    var hasElectricityMeter: Boolean,
    var hasProfessionalShops: Boolean,
    var numberOfMotorcycles: UInt,
    var numberOfCars: UInt,
    var numberOfPhones: UInt,
    var hasLaptop: Boolean,
    var hasFaxPhone: Boolean,
    var hasBankLoan: Boolean,
    var haveAntenna: Boolean,

): House {
}