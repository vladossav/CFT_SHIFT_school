package ru.savenkov.homework.data.datasource

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.savenkov.homework.data.model.Contact


@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("DELETE FROM contact")
    suspend fun deleteAll()

    @Query("SELECT * FROM contact")
    fun allContacts(): Flow<List<Contact>>

}