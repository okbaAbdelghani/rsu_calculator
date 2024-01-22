package com.example.calculatersunotes.data.models

class Region(
    var code: String = "",
    var name: String = "",
    var hierarchyLevel: Int = 0,
    var hierarchyName: String = "",
    var parentLocCode: String = "",
    var langCode: String = "",
    var isActive: Boolean = false,
    var kzgUrban: Double = 0.0,
    var kzgRural: Double = 0.0,

)