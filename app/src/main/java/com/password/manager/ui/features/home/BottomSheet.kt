package com.password.manager.ui.features.home

import androidx.compose.runtime.Composable
import com.password.manager.data.Password
import com.password.manager.ui.features.home.bottomsheet.AddOrUpdatePasswordSheet
import com.password.manager.ui.features.home.bottomsheet.ShowSavedPassword

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