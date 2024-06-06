package com.password.manager.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.password.manager.data.Password
import com.password.manager.domain.usecase.UseCaseDeletePassword
import com.password.manager.domain.usecase.UseCaseFetchAllPasswords
import com.password.manager.domain.usecase.UseCaseInsertPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCaseFetchAllPasswords: UseCaseFetchAllPasswords,
    private val useCaseInsertPassword: UseCaseInsertPassword,
    private val useCaseDeletePassword: UseCaseDeletePassword,
) : ViewModel() {

    private var _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllPasswords()
    }

    private fun getAllPasswords() {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseFetchAllPasswords.action(Unit).collect { result ->
                _uiState.emit(HomeUiState.Success(result))
            }
        }
    }

    fun addNewPassword(password: Password) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseInsertPassword.action(password)
        }
    }

    fun deletePassword(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            useCaseDeletePassword.action(id)
        }
    }
}