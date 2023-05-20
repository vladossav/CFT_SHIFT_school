package ru.savenkov.homework.ui.di

import dagger.Subcomponent
import ru.savenkov.homework.di.ActivityScope
import ru.savenkov.homework.ui.MainActivity

@ActivityScope
@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun factComponent(): FactsComponent.Factory
}