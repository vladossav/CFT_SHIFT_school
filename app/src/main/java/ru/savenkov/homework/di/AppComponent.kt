package ru.savenkov.homework.di

import dagger.Component
import ru.savenkov.homework.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}