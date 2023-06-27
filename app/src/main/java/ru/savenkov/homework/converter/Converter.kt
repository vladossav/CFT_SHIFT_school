package ru.savenkov.homework.converter

import android.content.res.Resources
import ru.savenkov.homework.R
import java.text.SimpleDateFormat
import java.util.*

object Converter {

    fun toLocalDateTime(dateTime: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
        val date = dateFormat.parse(dateTime)
        val localDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        return localDateFormat.format(date)
    }

    fun toLocalLanguage(resources: Resources, status: String): String = when (status) {
        "APPROVED" -> resources.getString(R.string.loan_status_approved)
        "REJECTED" -> resources.getString(R.string.loan_status_rejected)
        "REGISTERED" -> resources.getString(R.string.loan_status_registered)
        else -> throw java.lang.IllegalStateException()
    }


}