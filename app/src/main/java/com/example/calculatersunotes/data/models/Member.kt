package com.example.calculatersunotes.data.models

 interface Member{
     abstract var fullName: String
     var age: UShort
     var hasHealthCoverage: Boolean
     var isChildOfHouseHolder: Boolean
     var isPartnerOfHouseHolder: Boolean
 }