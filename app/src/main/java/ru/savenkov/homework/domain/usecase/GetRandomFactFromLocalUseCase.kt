package ru.savenkov.homework.domain.usecase

import ru.savenkov.homework.data.repository.RandomFactsRepositoryImpl
import ru.savenkov.homework.domain.repository.RandomFactsRepository
import javax.inject.Inject

class GetRandomFactFromLocalUseCase @Inject constructor(
    private val repository: RandomFactsRepository
){

    //TODO: DI
    //TODO: сделать так, чтобы repository не пересоздавался для каждого UseCase


    operator fun invoke(): String {
        val fromLocal = repository.getFromLocal()
        val repoInstanceHash = repository.hashCode()

        return "$fromLocal, repo hash = $repoInstanceHash"
    }
}