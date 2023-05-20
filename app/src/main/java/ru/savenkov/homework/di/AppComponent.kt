package ru.savenkov.homework.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.savenkov.homework.data.di.DataModule
import ru.savenkov.homework.domain.di.RepositoryModule
import ru.savenkov.homework.ui.di.MainComponent
import javax.inject.Singleton

@Singleton
@Component(modules = [ RepositoryModule::class, DataModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
}