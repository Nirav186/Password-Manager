package com.password.manager.ui.features.home.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.R
import com.password.manager.data.model.Password
import com.password.manager.ui.components.CTAButtonBase
import com.password.manager.ui.components.CustomTextField
import com.password.manager.ui.components.DragHandle
import com.password.manager.ui.features.home.HomeViewModel
import com.password.manager.ui.theme.Roboto

object AddOrUpdatePasswordSheetDefaults {
    val DefaultPadding: Dp = 30.dp
    val SpaceBetweenChild: Dp = 16.dp
    val DefaultTextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = Roboto,
        fontWeight = FontWeight.W500
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrUpdatePasswordSheet(password: Password? = null, closeSheet: () -> Unit) {
    val viewModel: HomeViewModel = hiltViewModel()
    val sheetState = rememberModalBottomSheetState()

    var accountNameTextFieldValue by rememberSaveable {
        mutableStateOf(password?.accountName ?: "")
    }
    var userNameOrEmailTextFieldValue by rememberSaveable {
        mutableStateOf(password?.userNameOrEmail ?: "")
    }
    var passwordTextFieldValue by rememberSaveable {
        mutableStateOf(password?.getDecryptPassword() ?: "")
    }

    var isErrorForAccountName by rememberSaveable { mutableStateOf(false) }
    var isErrorForUserNameOrEmail by rememberSaveable { mutableStateOf(false) }
    var isErrorForPassword by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) = text.isNotEmpty()

    ModalBottomSheet(sheetState = sheetState, onDismissRequest = { closeSheet() }, dragHandle = {
        DragHandle()
    }) {

        Column(
            modifier = Modifier.padding(AddOrUpdatePasswordSheetDefaults.DefaultPadding),
            verticalArrangement = Arrangement.spacedBy(AddOrUpdatePasswordSheetDefaults.SpaceBetweenChild)
        ) {
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = accountNameTextFieldValue,
                onValueChange = { accountNameTextFieldValue = it },
                placeholderText = stringResource(id = R.string.place_holder_account_name),
                isError = isErrorForAccountName,
                textStyle = AddOrUpdatePasswordSheetDefaults.DefaultTextStyle
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = userNameOrEmailTextFieldValue,
                onValueChange = { userNameOrEmailTextFieldValue = it },
                placeholderText = stringResource(id = R.string.place_holder_username_or_email),
                isError = isErrorForUserNameOrEmail,
                textStyle = AddOrUpdatePasswordSheetDefaults.DefaultTextStyle
            )

            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                value = passwordTextFieldValue,
                onValueChange = { passwordTextFieldValue = it },
                placeholderText = stringResource(id = R.string.place_holder_password),
                isError = isErrorForPassword,
                textStyle = AddOrUpdatePasswordSheetDefaults.DefaultTextStyle
            )

            CTAButtonBase(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.add_new_account),
                onClick = {
                    isErrorForAccountName = validate(accountNameTextFieldValue).not()
                    isErrorForUserNameOrEmail = validate(userNameOrEmailTextFieldValue).not()
                    isErrorForPassword = validate(passwordTextFieldValue).not()
                    if (isErrorForAccountName.not() && isErrorForUserNameOrEmail.not() && isErrorForPassword.not()) {
                        if (password?.id == null) {
                            viewModel.addNewPassword(
                                Password(
                                    id = null,
                                    accountName = accountNameTextFieldValue,
                                    userNameOrEmail = userNameOrEmailTextFieldValue,
                                    password = passwordTextFieldValue,
                                    secretKey = ""
                                )
                            )
                        } else {
                            password.accountName = accountNameTextFieldValue
                            password.userNameOrEmail = userNameOrEmailTextFieldValue
                            password.password = passwordTextFieldValue
                            viewModel.addNewPassword(password)
                        }
                        closeSheet()
                    }
                })
        }
    }
}