package com.example.calculatersunotes.data.models

class UrbanHouseHolder: HouseHolder, IUrbanMember {
    override var fullName: String = ""
    override var age: UShort = 0U
    override var hasHealthCoverage: Boolean = false
    override var isChildOfHouseHolder: Boolean = false
    override var isPartnerOfHouseHolder: Boolean = false
    override var hasDiploma: Boolean = false
    override var isSchooler: Boolean = false
    override var privateSector: Boolean = false
    override var publicSector: Boolean = false
    override var sphere: String = ""
    override var hasAJob: Boolean = false
    override var equipmentManagementActivity: Boolean = false
    override var hasHighProfessionalPosition: Boolean = false
    override var isRecruiting: Boolean = false
    var hasHighEducationLevel: Boolean = false
    var hasAverageProfessionalPosition:Boolean = false
    var isRetiredOrBeneficiaryOfIncome: Boolean = false

}