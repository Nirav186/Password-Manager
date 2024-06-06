package com.password.manager.ui.features.home

import com.password.manager.data.model.Password

sealed class HomeUiState {
    data object Loading : HomeUiState()
    class Success(val passwordList: List<Password>) : HomeUiState()
    data object Error : HomeUiState()
}