package com.password.manager.ui.features.home.bottomsheet

import androidx.compose.runtime.Composable
import com.password.manager.data.model.Password

@Composable
fun BottomSheet(
    bottomSheetType: BottomSheetType,
    closeSheet: () -> Unit,
    onEditClick: (Password) -> Unit,
    onDeleteClick: (Password) -> Unit
) {
    when (bottomSheetType) {
        is BottomSheetType.AddNewPassword -> AddOrUpdatePasswordSheet(closeSheet = closeSheet)
        is BottomSheetType.ShowSavedPassword -> ShowSavedPassword(
            password = bottomSheetType.password,
            closeSheet = closeSheet,
            onEditClick = onEditClick,
            onDeleteClick = onDeleteClick
        )

        is BottomSheetType.EditSavedPassword -> AddOrUpdatePasswordSheet(
            password = bottomSheetType.password,
            closeSheet = closeSheet,
        )
    }
}