package ru.savenkov.homework

import android.app.Application
import ru.savenkov.homework.di.AppComponent
import ru.savenkov.homework.di.DaggerAppComponent

class App: Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()

}