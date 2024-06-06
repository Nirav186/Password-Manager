package com.password.manager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.password.manager.data.dao.PasswordDao
import com.password.manager.data.model.Password

@Database(
    entities = [Password::class],
    exportSchema = false,
    version = 1
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun getPasswordDao(): PasswordDao

}