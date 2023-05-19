package ru.savenkov.homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.savenkov.homework.shared.contacts.data.model.Contact
import ru.savenkov.homework.shared.contacts.domain.usecase.*

class ContactDialogViewModel(
    private val deleteContactUseCase: DeleteContactUseCase,
    private val editContactUseCase: EditContactUseCase,
    private val addContactUseCase: AddContactUseCase
): ViewModel() {

    fun updateContact(contact: Contact) = viewModelScope.launch {
        editContactUseCase.execute(contact)
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch {
        deleteContactUseCase.execute(contact)
    }

    fun addContact(contact: Contact) = viewModelScope.launch {
        addContactUseCase.execute(contact)
    }
}