package ru.savenkov.homework.presentation

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import ru.savenkov.homework.data.model.Contact
import ru.savenkov.homework.domain.usecase.AddContactUseCase
import ru.savenkov.homework.domain.usecase.DeleteAllContactsUseCase

class MainActivityViewModel(private val app: App): AndroidViewModel(application = app) {
    private val deleteAllContactsUseCase = DeleteAllContactsUseCase(app.contactRepository)
    private val getAllContactsUseCase = DeleteAllContactsUseCase(app.contactRepository)
    private val addContactUseCase = AddContactUseCase(app.contactRepository)

    var contactList = (app.database).contactDao().allContacts().asLiveData()

    fun clearAll() = deleteAllContactsUseCase.execute()
    fun insertContact(contact: Contact) = addContactUseCase.execute(contact)
}