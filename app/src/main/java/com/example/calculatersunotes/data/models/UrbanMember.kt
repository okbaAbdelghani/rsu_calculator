package com.example.calculatersunotes.data.models

class UrbanMember(
    override var hasDiploma: Boolean,
    override var isSchooler: Boolean,
    override var privateSector: Boolean,
    override var publicSector: Boolean,
    override var hasAJob: Boolean,
    override var equipmentManagementActivity: Boolean,
    override var highProfessionalPosition: Boolean,
    override var fullName: String,
    override var hasHealthCoverage: Boolean,
    override var isChildOfHouseHolder: Boolean,
    override var age: UShort,
    override var isPartnerOfHouseHolder: Boolean
) : IUrbanMember {
}