package ru.savenkov.homework.api

data class ConvertApiResponse(
    val responseRaw: String?,
    val responseError: String?,
    val responseBody: FileResponse?
)
