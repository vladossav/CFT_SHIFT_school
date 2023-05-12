package ru.savenkov.homework.domain.usecase

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.savenkov.homework.domain.repository.ContactRepository

class DeleteAllContactsUseCase(private val contactRepository: ContactRepository) {
    fun execute() = GlobalScope.launch {
        contactRepository.deleteAll()
    }
}