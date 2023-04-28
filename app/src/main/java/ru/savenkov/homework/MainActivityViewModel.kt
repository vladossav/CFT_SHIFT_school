package ru.savenkov.homework

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val db: ContactDao): ViewModel() {
    var contactList = db.allContacts().asLiveData()

    fun insertToDatabase(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        db.insert(contact)
    }

    fun clearAllFromDatabase() = viewModelScope.launch(Dispatchers.IO) {
        db.deleteAll()
    }

    fun deleteFromDatabaseById(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        db.delete(id)
    }

    fun updateContactById(id: Int, newName: String, newPhone: String) = viewModelScope.launch(Dispatchers.IO) {
        db.update(id,newName,newPhone)
    }



    class ViewModelFactory(private val db: ContactDao) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}