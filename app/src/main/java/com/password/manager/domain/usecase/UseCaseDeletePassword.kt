package com.password.manager.domain.usecase

import com.password.manager.core.UseCase
import com.password.manager.domain.PasswordRepository
import javax.inject.Inject

class UseCaseDeletePassword @Inject constructor(private val passwordRepository: PasswordRepository) :
    UseCase<Unit, Long>() {
    override suspend fun action(params: Long) = passwordRepository.deletePassword(params)
}