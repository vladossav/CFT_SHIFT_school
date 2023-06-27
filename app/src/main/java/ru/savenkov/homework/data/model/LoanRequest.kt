package ru.savenkov.homework.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoanRequest(
    val amount: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val percent: Float = 0f,
    val period: Int = 0,
    val phoneNumber: String = ""
)
