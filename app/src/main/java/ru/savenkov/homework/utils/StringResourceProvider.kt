package ru.savenkov.homework.utils

import android.content.Context
import javax.inject.Inject

class StringResourceProvider @Inject constructor(
    private val context: Context
) {
    fun getString(resId: Int): String = context.getString(resId)
}