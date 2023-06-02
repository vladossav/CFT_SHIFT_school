package ru.savenkov.homework.api

import com.squareup.moshi.Json

data class FileResponse(
    @Json
    val FileId: String,
    @Json
    val FileName: String,
    @Json
    val FileExt: String
)
