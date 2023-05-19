package ru.savenkov.homework.shared.contacts.domain.usecase

import ru.savenkov.homework.shared.contacts.domain.repository.ContactRepository

class DeleteAllContactsUseCase(private val contactRepository: ContactRepository) {
    suspend fun execute() = contactRepository.deleteAll()
}