package ru.savenkov.homework.shared.contacts.domain.usecase

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.savenkov.homework.shared.contacts.data.model.Contact
import ru.savenkov.homework.shared.contacts.domain.repository.ContactRepository

class DeleteContactUseCase(private val contactRepository: ContactRepository) {
    fun execute(contact: Contact) = GlobalScope.launch {
        contactRepository.deleteContact(contact)
    }
}