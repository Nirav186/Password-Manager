package com.password.manager.data.repository

import com.password.manager.data.Password
import com.password.manager.data.dao.PasswordDao
import com.password.manager.domain.PasswordRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PasswordRepositoryImpl @Inject constructor(
    private val passwordDao: PasswordDao
) : PasswordRepository {
    override fun insertPassword(password: Password) = passwordDao.addNewPassword(password)

    override fun fetchAllPasswords() = passwordDao.getAllPasswords()

    override fun deletePassword(id: Long) = passwordDao.deletePassword(id)
}