package ru.savenkov.homework.ui.di

import dagger.Subcomponent
import ru.savenkov.homework.di.FragmentScope
import ru.savenkov.homework.ui.FactsFragment

@FragmentScope
@Subcomponent
interface FactsComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): FactsComponent
    }

    fun inject(fragment: FactsFragment)
}