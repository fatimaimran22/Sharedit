package com.example.sharedit

data class File(
    var pic: String,
    var name: String,
    var date: String,
    var time: String,
    var name_d: String,
    var date_d: String,
    var time_d: String,
    var isEditing: Boolean = false,
    val isImage: Boolean = false
)
