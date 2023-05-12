package ru.savenkov.homework.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.savenkov.homework.data.model.Contact

interface ContactRepository {
    suspend fun addContact(contact: Contact)
    suspend fun updateContact(contact: Contact)
    suspend fun deleteContact(contact: Contact)
    suspend fun deleteAll()
    suspend fun getAll(): Flow<List<Contact>>
}