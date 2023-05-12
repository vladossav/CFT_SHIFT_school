package ru.savenkov.homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.savenkov.homework.data.ContactDao
import ru.savenkov.homework.data.Contact

class MainActivityViewModel(private val db: ContactDao): ViewModel() {
    var contactList = db.allContacts().asLiveData()

    fun insertContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        db.insert(contact)
    }

    fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        db.deleteAll()
    }

    fun deleteContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        db.delete(contact)
    }

    fun updateContact(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        db.update(contact)
    }
}