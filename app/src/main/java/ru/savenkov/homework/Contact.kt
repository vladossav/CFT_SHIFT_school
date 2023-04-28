package ru.savenkov.homework

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact")
data class Contact(var name: String, var phone: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}