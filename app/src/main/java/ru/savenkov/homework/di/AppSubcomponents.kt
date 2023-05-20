package ru.savenkov.homework.di

import dagger.Module
import ru.savenkov.homework.ui.di.MainComponent

@Module(
    subcomponents = [
        MainComponent::class
    ]
)
class AppSubcomponents