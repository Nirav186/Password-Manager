package com.password.manager.domain.usecase

import com.password.manager.core.UseCase
import com.password.manager.data.model.Password
import com.password.manager.domain.PasswordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCaseFetchAllPasswords @Inject constructor(private val passwordRepository: PasswordRepository) :
    UseCase<Flow<List<Password>>, Unit>() {
    override suspend fun action(params: Unit) = passwordRepository.fetchAllPasswords()
}