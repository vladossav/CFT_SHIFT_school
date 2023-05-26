package ru.savenkov.homework.di

import dagger.Binds
import dagger.Module
import ru.savenkov.homework.LocalDataSource
import ru.savenkov.homework.Repository
import ru.savenkov.homework.data.DataSource
import ru.savenkov.homework.data.RepositoryImpl
import javax.inject.Singleton

@Module
interface DataModule {
    @Singleton
    @Binds
    fun bindLocalDataSource(dataSource: LocalDataSource): DataSource

    @Singleton
    @Binds
    fun bindRepository(repository: RepositoryImpl): Repository
}