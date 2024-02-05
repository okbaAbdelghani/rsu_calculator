package com.example.calculatersunotes.data.models

class UrbanMember(
    override var hasDiploma: Boolean = false,
    override var isSchooler: Boolean = false,
    override var privateSector: Boolean = false,
    override var publicSector: Boolean = false,
    override var hasAJob: Boolean = false,
    override var equipmentManagementActivity: Boolean = false,
    override var hasHighProfessionalPosition: Boolean = false,
    override var fullName: String = "",
    override var hasHealthCoverage: Boolean = false,
    override var isChildOfHouseHolder: Boolean = false,
    override var age: UShort = 0u,
    override var isPartnerOfHouseHolder: Boolean = false
) : IUrbanMember {

    override fun toString(): String {
        return """
            {
                "hasDiploma": $hasDiploma,
                "isSchooler": $isSchooler,
                "privateSector": $privateSector,
                "publicSector": $publicSector,
                "hasAJob": $hasAJob,
                "equipmentManagementActivity": $equipmentManagementActivity,
                "hasHighProfessionalPosition": $hasHighProfessionalPosition,
                "fullName": "$fullName",
                "hasHealthCoverage": $hasHealthCoverage,
                "isChildOfHouseHolder": $isChildOfHouseHolder,
                "age": $age,
                "isPartnerOfHouseHolder": $isPartnerOfHouseHolder
            }
        """.trimIndent()
    }
}