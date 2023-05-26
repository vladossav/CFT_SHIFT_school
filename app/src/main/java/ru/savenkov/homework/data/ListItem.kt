package ru.savenkov.homework.data

sealed class ListItem {

    class StudentItem(
        val name: String,
        val secondName: String,
        val description: String,
        val hasPortfolio: Boolean,
    ) : ListItem()

    class BannerItem(
        val title: String,
        val description: String,
    ) : ListItem()
}
