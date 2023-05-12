package ru.savenkov.homework.domain.repository

import ru.savenkov.homework.data.Contact

interface ContactRepository {
    fun addContact(contact: Contact)
    fun updateContact(contact: Contact)
    fun deleteContact(contact: Contact)
    fun deleteAll()
}