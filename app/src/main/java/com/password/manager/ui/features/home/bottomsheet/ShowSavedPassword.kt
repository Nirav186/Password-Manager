package com.password.manager.ui.features.home.bottomsheet

import android.util.Base64
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.password.manager.R
import com.password.manager.core.encryption.EncryptDecrypt
import com.password.manager.data.Password
import com.password.manager.ui.components.CTAButtonBase
import com.password.manager.ui.components.DragHandle
import com.password.manager.ui.theme.BlueHeaderTextColor
import com.password.manager.ui.theme.DarkTextColor
import com.password.manager.ui.theme.NegativeButtonColor
import com.password.manager.ui.theme.PlaceHolderTextColor
import com.password.manager.ui.theme.Roboto
import com.password.manager.ui.theme.SecondaryIconTintColor
import com.password.manager.ui.theme.SfProDisplay

object ShowSavedPasswordDefaults {
    val DefaultPadding: Dp = 30.dp
    val HeaderTextColor: Color = BlueHeaderTextColor
    val HeaderTextSize: TextUnit = 24.sp
    val PrimaryTextSize: TextUnit = 20.sp
    val SecondaryTextSize: TextUnit = 14.sp
    val PrimaryTextColor: Color = DarkTextColor
    val SecondaryTextColor: Color = PlaceHolderTextColor
    val PasswordToggleTintColor: Color = SecondaryIconTintColor
    val DeleteButtonColor: Color = NegativeButtonColor
    val SpaceBetweenChild: Dp = 22.dp
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowSavedPassword(
    password: Password?,
    closeSheet: () -> Unit,
    onEditClick: (Password) -> Unit,
    onDeleteClick: (Password) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    var isPasswordVisible by rememberSaveable {
        mutableStateOf(false)
    }
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { closeSheet() },
        dragHandle = {
            DragHandle()
        }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ShowSavedPasswordDefaults.DefaultPadding)
        ) {
            Text(
                text = stringResource(R.string.header_account_details),
                style = TextStyle(
                    color = ShowSavedPasswordDefaults.HeaderTextColor,
                    fontSize = ShowSavedPasswordDefaults.HeaderTextSize,
                    fontFamily = SfProDisplay,
                    fontWeight = FontWeight.W600
                )
            )

            Text(
                modifier = Modifier.padding(top = ShowSavedPasswordDefaults.SpaceBetweenChild),
                text = stringResource(id = R.string.account_type),
                style = TextStyle(
                    color = ShowSavedPasswordDefaults.SecondaryTextColor,
                    fontSize = ShowSavedPasswordDefaults.SecondaryTextSize,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.W500
                )
            )

            Text(
                text = password?.accountName ?: "",
                style = TextStyle(
                    color = ShowSavedPasswordDefaults.PrimaryTextColor,
                    fontSize = ShowSavedPasswordDefaults.PrimaryTextSize,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.W600
                )
            )

            Text(
                modifier = Modifier.padding(top = ShowSavedPasswordDefaults.SpaceBetweenChild),
                text = stringResource(id = R.string.place_holder_username_or_email),
                style = TextStyle(
                    color = ShowSavedPasswordDefaults.SecondaryTextColor,
                    fontSize = ShowSavedPasswordDefaults.SecondaryTextSize,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.W500
                )
            )

            Text(
                text = password?.userNameOrEmail ?: "",
                style = TextStyle(
                    color = ShowSavedPasswordDefaults.PrimaryTextColor,
                    fontSize = ShowSavedPasswordDefaults.PrimaryTextSize,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.W600
                )
            )

            Text(
                modifier = Modifier.padding(top = ShowSavedPasswordDefaults.SpaceBetweenChild),
                text = stringResource(id = R.string.place_holder_password),
                style = TextStyle(
                    color = ShowSavedPasswordDefaults.SecondaryTextColor,
                    fontSize = ShowSavedPasswordDefaults.SecondaryTextSize,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.W500
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = if (isPasswordVisible) {
                        password?.getDecryptPassword() ?: ""
                    } else {
                        "********"
                    },
                    style = TextStyle(
                        color = ShowSavedPasswordDefaults.PrimaryTextColor,
                        fontSize = ShowSavedPasswordDefaults.PrimaryTextSize,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.W600
                    )
                )

                Icon(
                    imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "Visibility Toggle",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(
                            onClick = {
                                isPasswordVisible = !isPasswordVisible
                            }
                        )
                        .align(Alignment.CenterVertically),
                    tint = ShowSavedPasswordDefaults.PasswordToggleTintColor
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = ShowSavedPasswordDefaults.SpaceBetweenChild),
                horizontalArrangement = Arrangement.spacedBy(ShowSavedPasswordDefaults.DefaultPadding)
            ) {
                CTAButtonBase(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.edit),
                    onClick = {
                        password?.let {
                            onEditClick(it)
                        }
                    }
                )
                CTAButtonBase(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.delete),
                    onClick = {
                        password?.let {
                            onDeleteClick(it)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ShowSavedPasswordDefaults.DeleteButtonColor,
                    )
                )
            }
        }
    }
}