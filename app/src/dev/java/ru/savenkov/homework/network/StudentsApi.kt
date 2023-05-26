package ru.savenkov.homework.network

import retrofit2.http.GET
import ru.savenkov.homework.data.ListItem


interface StudentsApi {

    @GET("students")
    suspend fun getStudentsList(): List<ListItem>
}