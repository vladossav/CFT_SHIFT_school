package ru.savenkov.homework.shared.contacts.data.repository

import ru.savenkov.homework.shared.contacts.data.model.Contact
import ru.savenkov.homework.shared.contacts.data.datasource.ContactDao
import ru.savenkov.homework.shared.contacts.domain.repository.ContactRepository

class ContactRepositoryImpl(private val db: ContactDao): ContactRepository {

    override suspend fun addContact(contact: Contact) = db.insert(contact)

    override suspend fun updateContact(contact: Contact) = db.update(contact)

    override suspend fun deleteContact(contact: Contact) = db.delete(contact)

    override suspend fun deleteAll() = db.deleteAll()
    override fun getAll(): kotlinx.coroutines.flow.Flow<List<Contact>> = db.allContacts()
}