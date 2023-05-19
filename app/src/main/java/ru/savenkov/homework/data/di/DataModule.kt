package ru.savenkov.homework.data.di


import dagger.Binds
import dagger.Module
import ru.savenkov.homework.data.datasource.RandomFactsDataSource
import ru.savenkov.homework.data.datasource.RandomFactsLocalDataSource
import ru.savenkov.homework.data.datasource.RandomFactsRemoteDataSource
import javax.inject.Named

@Module
interface DataModule {

    @Named("local")
    @Binds
    fun provideLocalDataSource(dataSource: RandomFactsLocalDataSource): RandomFactsDataSource

    @Named("remote")
    @Binds
    fun provideRemoteDataSource(dataSource: RandomFactsRemoteDataSource): RandomFactsDataSource
}