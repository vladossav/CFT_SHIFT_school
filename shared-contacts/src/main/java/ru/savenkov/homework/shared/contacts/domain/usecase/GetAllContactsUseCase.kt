package ru.savenkov.homework.shared.contacts.domain.usecase

import ru.savenkov.homework.shared.contacts.domain.repository.ContactRepository

class GetAllContactsUseCase(private val contactRepository: ContactRepository) {
    fun execute() = contactRepository.getAll()
}