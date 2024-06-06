package com.password.manager.domain

import com.password.manager.data.model.Password
import kotlinx.coroutines.flow.Flow

interface PasswordRepository {
    fun insertPassword(password: Password): Long
    fun fetchAllPasswords(): Flow<List<Password>>
    fun deletePassword(id: Long)
}