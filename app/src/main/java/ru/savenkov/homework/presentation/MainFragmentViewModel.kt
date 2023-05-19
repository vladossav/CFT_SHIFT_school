package ru.savenkov.homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.savenkov.homework.shared.contacts.data.model.Contact
import ru.savenkov.homework.shared.contacts.domain.usecase.AddContactUseCase
import ru.savenkov.homework.shared.contacts.domain.usecase.DeleteAllContactsUseCase
import ru.savenkov.homework.shared.contacts.domain.usecase.GetAllContactsUseCase

class MainFragmentViewModel(
    private val deleteAllContactsUseCase: DeleteAllContactsUseCase,
    private val getAllContactsUseCase: GetAllContactsUseCase,
    private val addContactUseCase: AddContactUseCase
): ViewModel() {

    var contactList = getAllContactsUseCase.execute().asLiveData()


    fun clearAll() = viewModelScope.launch {
        deleteAllContactsUseCase.execute()
    }

    fun insertContact(contact: Contact) = viewModelScope.launch {
        addContactUseCase.execute(contact)
    }
}