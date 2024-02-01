package com.example.calculatersunotes.data.models

class UrbanHouse(
    override var bigGasBottlesInMonth: Double = 0.0,
    override var smallGasBottlesInMonth: Double = 0.0,
    override var electricityBillMonthOne: Double = 0.0,
    override var electricityBillMonthTwo: Double = 0.0,
    override var electricityBillMonthThree: Double = 0.0,
    override var waterBillMonthOne: Double = 0.0,
    override var waterBillMonthTwo: Double = 0.0,
    override var waterBillMonthThree: Double = 0.0,
    override var internetAndPhoneExpensesInMonth: Double = 0.0,
    override var roomsNumber: UInt = 0u,
    var hasShower: Boolean = false,
    var hasElectricityMeter: Boolean = false,
    var hasGarage: Boolean = false,
    var hasProfessionalShops: Boolean = false,
    var numberOfMotorcycles: UInt = 0u,
    var numberOfCars: UInt = 0u,
    var numberOfPhones: UInt = 0u,
    var hasLaptop: Boolean = false,
    var hasFixPhone: Boolean = false,
    var hasBankLoan: Boolean = false,
    var hasAntenna: Boolean = false,

    ): House {
}