package ru.savenkov.homework.domain.di

import dagger.Binds
import dagger.Module
import ru.savenkov.homework.data.repository.RandomFactsRepositoryImpl
import ru.savenkov.homework.domain.repository.RandomFactsRepository
import javax.inject.Singleton


@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindRepository(impl: RandomFactsRepositoryImpl): RandomFactsRepository

}