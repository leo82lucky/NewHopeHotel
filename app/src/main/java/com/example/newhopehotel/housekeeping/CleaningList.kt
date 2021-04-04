package com.example.newhopehotel.housekeeping

data class CleaningList(
    val room: String,
    val time: String,
    var isChecked: Boolean
)