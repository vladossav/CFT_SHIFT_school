package ru.savenkov.homework.domain.usecase

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.savenkov.homework.data.model.Contact
import ru.savenkov.homework.domain.repository.ContactRepository

class DeleteContactUseCase(private val contactRepository: ContactRepository) {
    fun execute(contact: Contact) = GlobalScope.launch {
        contactRepository.deleteContact(contact)
    }
}