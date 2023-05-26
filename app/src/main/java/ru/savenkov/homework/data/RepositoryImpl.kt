package ru.savenkov.homework.data

import ru.savenkov.homework.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataSource: DataSource
): Repository {

    override suspend fun getData(): List<ListItem> = dataSource.getData()
}