package ru.savenkov.homework.domain.usecase

import ru.savenkov.homework.data.model.Contact
import ru.savenkov.homework.domain.repository.ContactRepository

class GetAllContactsUseCase(private val contactRepository: ContactRepository) {
    suspend fun execute(contact: Contact) = contactRepository.getAll()
}