package ru.savenkov.homework

import ru.savenkov.homework.data.DataSource
import ru.savenkov.homework.data.ListItem
import ru.savenkov.homework.network.StudentsApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: StudentsApi
): DataSource {
    override suspend fun getData(): List<ListItem> = api.getStudentsList()

}