package com.example.calculatersunotes.data.models

interface IUrbanMember: Member {
    var hasDiploma: Boolean
    var isSchooler: Boolean
    var privateSector: Boolean
    var publicSector: Boolean
    var hasAJob: Boolean
    var equipmentManagementActivity: Boolean
    var hasHighProfessionalPosition: Boolean
}