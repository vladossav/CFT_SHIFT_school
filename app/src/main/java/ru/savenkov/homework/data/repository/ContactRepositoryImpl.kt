package ru.savenkov.homework.data.repository

import ru.savenkov.homework.data.model.Contact
import ru.savenkov.homework.data.datasource.ContactDao
import ru.savenkov.homework.domain.repository.ContactRepository

class ContactRepositoryImpl(private val db: ContactDao): ContactRepository {

    override suspend fun addContact(contact: Contact) = db.insert(contact)

    override suspend fun updateContact(contact: Contact) = db.update(contact)

    override suspend fun deleteContact(contact: Contact) = db.delete(contact)

    override suspend fun deleteAll() = db.deleteAll()
    override suspend fun getAll(): kotlinx.coroutines.flow.Flow<List<Contact>> = db.allContacts()
}