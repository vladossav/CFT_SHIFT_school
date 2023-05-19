package ru.savenkov.homework.data.repository

import ru.savenkov.homework.data.datasource.RandomFactsDataSource
import ru.savenkov.homework.domain.repository.RandomFactsRepository
import javax.inject.Inject
import javax.inject.Named

class RandomFactsRepositoryImpl @Inject constructor(
    @Named("local") private val localDataSource: RandomFactsDataSource,
    @Named("remote") private val remoteDataSource: RandomFactsDataSource
) : RandomFactsRepository {

    override fun getFromRemote(): String =
        remoteDataSource.get()

    override fun getFromLocal(): String =
        localDataSource.get()
}