package ru.savenkov.homework.domain

import ru.savenkov.homework.data.Contact
import ru.savenkov.homework.domain.repository.ContactRepository

class AddContactUseCase(private val contactRepository: ContactRepository) {
    fun execute(contact: Contact) = contactRepository.addContact(contact)
}