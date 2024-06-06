package com.password.manager.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.password.manager.ui.theme.BottomSheetDockedContainerColor

internal object SheetBottomTokens {
    val DockedDragHandleColor = BottomSheetDockedContainerColor
    val DockedDragHandleHeight = 4.0.dp
    val DockedDragHandleWidth = 46.0.dp
    val DragHandleVerticalPadding =  9.dp
}

@Composable
fun DragHandle(
    modifier: Modifier = Modifier,
    width: Dp = SheetBottomTokens.DockedDragHandleWidth,
    height: Dp = SheetBottomTokens.DockedDragHandleHeight,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    color: Color = SheetBottomTokens.DockedDragHandleColor,
    verticalPadding: Dp = SheetBottomTokens.DragHandleVerticalPadding
) {
    val context = LocalContext.current
    val dragHandleDescription = context.getString(
        androidx.compose.material3.R.string.bottom_sheet_drag_handle_description
    )
    Surface(
        modifier = modifier
            .padding(vertical = verticalPadding)
            .semantics { contentDescription = dragHandleDescription },
        color = color,
        shape = shape
    ) {
        Box(
            Modifier
                .size(
                    width = width,
                    height = height
                )
        )
    }
}