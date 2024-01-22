package com.example.calculatersunotes.data.models

class RuralHouseHolder: HouseHolder, IRuralMember {
    override var fullName: String = ""
    override var age: UShort = 0U
    override var hasHealthCoverage: Boolean = false
    override var isChildOfHouseHolder: Boolean = false
    override var isPartnerOfHouseHolder: Boolean = false
    override var sphere: String = ""
    override var hasHighProfessionalPosition: Boolean = false
    override var isRecruiting: Boolean = false
    override var isLiterate: Boolean = false
    override var hasHighEducationDiploma: Boolean = false
    override var isMerchant: Boolean = false
    var hasBasicEducation: Boolean = false
    var fixEquipmentOrMachinery: Boolean = false
    var isCraftsman: Boolean = false
    var isFarmer: Boolean = false



}