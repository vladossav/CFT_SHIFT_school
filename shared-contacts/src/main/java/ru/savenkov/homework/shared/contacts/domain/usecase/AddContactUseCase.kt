package ru.savenkov.homework.shared.contacts.domain.usecase

import ru.savenkov.homework.shared.contacts.data.model.Contact
import ru.savenkov.homework.shared.contacts.domain.repository.ContactRepository

class AddContactUseCase(private val contactRepository: ContactRepository) {
    suspend fun execute(contact: Contact) = contactRepository.addContact(contact)

}