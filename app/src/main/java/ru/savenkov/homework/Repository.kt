package ru.savenkov.homework

import ru.savenkov.homework.data.ListItem

interface Repository {
    suspend fun getData(): List<ListItem>
}