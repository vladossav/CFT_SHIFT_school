package ru.savenkov.homework

import android.app.Application
import ru.savenkov.homework.di.AppComponent
import ru.savenkov.homework.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}