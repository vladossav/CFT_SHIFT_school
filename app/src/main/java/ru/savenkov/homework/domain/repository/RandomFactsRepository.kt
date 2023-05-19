package ru.savenkov.homework.domain.repository

interface RandomFactsRepository {

    fun getFromRemote(): String

    fun getFromLocal(): String
}