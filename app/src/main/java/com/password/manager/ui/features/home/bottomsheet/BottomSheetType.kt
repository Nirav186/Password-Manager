package com.password.manager.ui.features.home.bottomsheet

import com.password.manager.data.model.Password

sealed class BottomSheetType {
    data object AddNewPassword : BottomSheetType()
    data class ShowSavedPassword(val password: Password) : BottomSheetType()
    data class EditSavedPassword(val password: Password) : BottomSheetType()
}