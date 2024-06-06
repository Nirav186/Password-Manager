package com.password.manager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.password.manager.data.model.Password
import kotlinx.coroutines.flow.Flow

@Dao
interface PasswordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewPassword(password: Password): Long

    @Query("select * from Password")
    fun getAllPasswords(): Flow<List<Password>>

    @Query("delete from Password where id is (:mId)")
    fun deletePassword(mId: Long)

}