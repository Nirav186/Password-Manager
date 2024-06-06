package com.password.manager.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.password.manager.R
import com.password.manager.data.model.Password
import com.password.manager.ui.features.home.bottomsheet.BottomSheet
import com.password.manager.ui.features.home.bottomsheet.BottomSheetType
import com.password.manager.ui.theme.FloatingButtonContainerColor
import com.password.manager.ui.theme.HomeScreenColors
import com.password.manager.ui.theme.SfProDisplay

object HomeScreenDefaults {
    val BackGroundColor = HomeScreenColors.BackGroundColor
    val DividerColor: Color = HomeScreenColors.DividerColor
    val DividerThickness: Dp = 1.dp
}

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState().value

    var isSheetIsOpen by rememberSaveable {
        mutableStateOf(false)
    }

    var bottomSheetType: BottomSheetType by remember {
        mutableStateOf(BottomSheetType.AddNewPassword)
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                containerColor = FloatingButtonContainerColor,
                onClick = {
                    bottomSheetType = BottomSheetType.AddNewPassword
                    isSheetIsOpen = true
                }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }) {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(HomeScreenDefaults.BackGroundColor)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                text = stringResource(R.string.title_password_manager),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = SfProDisplay,
                    fontWeight = FontWeight.Bold
                )
            )
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = HomeScreenDefaults.DividerThickness,
                color = HomeScreenDefaults.DividerColor
            )
            when (uiState) {
                is HomeUiState.Loading -> {
                    // need to show loader
                }

                is HomeUiState.Success -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(uiState.passwordList) { password ->
                            SavedPasswordItem(password) {
                                bottomSheetType = BottomSheetType.ShowSavedPassword(password)
                                isSheetIsOpen = true
                            }
                        }
                    }
                }

                is HomeUiState.Error -> {
                    // need to show error
                }
            }
        }

        if (isSheetIsOpen) {
            BottomSheet(
                bottomSheetType = bottomSheetType,
                closeSheet = {
                    isSheetIsOpen = false
                },
                onEditClick = { password: Password ->
                    bottomSheetType = BottomSheetType.EditSavedPassword(password)
                    isSheetIsOpen = true
                },
                onDeleteClick = { password: Password ->
                    password.id?.let { id ->
                        viewModel.deletePassword(id)
                        isSheetIsOpen = false
                    }
                }
            )
        }
    }
}