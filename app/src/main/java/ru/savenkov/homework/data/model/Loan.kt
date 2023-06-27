package ru.savenkov.homework.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Loan(
    val amount: Int = 0,
    val date: String = "",
    val firstName: String = "",
    val id: Long = 0,
    val lastName: String = "",
    val percent: Double = 0.0,
    val period: Int = 0,
    val phoneNumber: String = "",
    val state: String = ""
)