package ru.savenkov.homework.shared.contacts.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.savenkov.homework.shared.contacts.data.model.Contact

interface ContactRepository {
    suspend fun addContact(contact: Contact)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
    suspend fun deleteAll()
    fun getAll(): Flow<List<Contact>>
}