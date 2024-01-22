package com.example.calculatersunotes.data.models

interface House {
    var bigGasBottlesInMonth: Double
    var smallGasBottlesInMonth: Double
    var electricityBillMonthOne: Double
    var electricityBillMonthTwo: Double
    var electricityBillMonthThree: Double
    var waterBillMonthOne: Double
    var waterBillMonthTwo: Double
    var waterBillMonthThree: Double
    var internetAndPhoneExpensesInMonth: Double
    var roomsNumber: UInt
}