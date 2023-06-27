package ru.savenkov.homework.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoanCondition(
    val maxAmount: Long = 0,
    val percent: Float = 0f,
    val period: Int = 0
)
