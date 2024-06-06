package com.password.manager.ui.features.home

import com.password.manager.data.Password

sealed class BottomSheetType {
    data object AddNewPassword : BottomSheetType()
    data class ShowSavedPassword(val password: Password) : BottomSheetType()
    data class EditSavedPassword(val password: Password) : BottomSheetType()
}