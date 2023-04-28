package ru.savenkov.homework

import android.app.Application

class AppInit: Application() {
    val database by lazy {
        AppDatabase.getInstance(this)
    }
}