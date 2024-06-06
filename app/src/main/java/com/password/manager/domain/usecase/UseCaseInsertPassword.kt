package com.password.manager.domain.usecase

import com.password.manager.core.UseCase
import com.password.manager.core.encryption.EncryptDecrypt
import com.password.manager.data.model.Password
import com.password.manager.domain.PasswordRepository
import javax.inject.Inject

class UseCaseInsertPassword @Inject constructor(
    private val passwordRepository: PasswordRepository
) :
    UseCase<Long, Password>() {
    override suspend fun action(params: Password): Long {
        val encryptionResult = EncryptDecrypt.encryptAES(
            params.password ?: ""
        )
        params.password = encryptionResult.first
        params.secretKey = encryptionResult.second
        return passwordRepository.insertPassword(params)
    }
}