package ru.savenkov.homework.di

import dagger.Binds
import dagger.Module
import ru.savenkov.homework.data.DataSource
import ru.savenkov.homework.RemoteDataSource
import ru.savenkov.homework.Repository
import ru.savenkov.homework.data.RepositoryImpl
import javax.inject.Singleton
import javax.sql.CommonDataSource

@Module
interface DataModule {
    @Singleton
    @Binds
    fun bindRemoteDataSource(dataSource: RemoteDataSource): DataSource

    @Singleton
    @Binds
    fun bindRepository(repository: RepositoryImpl): Repository
}