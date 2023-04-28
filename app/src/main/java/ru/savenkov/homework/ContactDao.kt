package ru.savenkov.homework

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {
    @Insert
    suspend fun insert(contact: Contact)

    @Query("UPDATE contact SET name=:newName, phone=:newPhone WHERE id=:id")
    suspend fun update(id: Int, newName: String, newPhone: String)

    @Query("DELETE FROM contact WHERE id=:id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM contact")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact")
    fun allContacts(): Flow<MutableList<Contact>>

}