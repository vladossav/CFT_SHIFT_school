package ru.savenkov.homework.di

import dagger.Component
import ru.savenkov.homework.MainActivity
import ru.savenkov.homework.data.di.DataModule
import ru.savenkov.homework.domain.di.RepositoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ RepositoryModule::class, DataModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}