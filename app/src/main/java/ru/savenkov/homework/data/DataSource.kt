package ru.savenkov.homework.data

interface DataSource {
   suspend fun getData(): List<ListItem>
}