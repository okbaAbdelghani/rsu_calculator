package com.example.calculatersunotes.data.models

class RuralMember(
            override var fullName: String,
            override var isLiterate: Boolean = false,
            override var hasHighEducationDiploma: Boolean = false,
            override var isMerchant: Boolean = false,
            override var age: UShort = 0u,
            override var hasHealthCoverage: Boolean = false,
            override var isChildOfHouseHolder: Boolean = false,
            override var isPartnerOfHouseHolder: Boolean = false
) : IRuralMember {


}