package ru.savenkov.homework.presentation

import android.app.Application
import ru.savenkov.homework.shared.contacts.data.datasource.AppDatabase
import ru.savenkov.homework.shared.contacts.data.repository.ContactRepositoryImpl

class App: Application() {
    val database by lazy {
        AppDatabase.getInstance(this)
    }
    val contactRepository by lazy {
        ContactRepositoryImpl(database.contactDao())
    }
}