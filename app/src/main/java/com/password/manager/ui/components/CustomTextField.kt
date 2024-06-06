package com.password.manager.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.password.manager.ui.theme.CustomTextFieldColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    isError: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(CustomTextFieldColors.FocusedCursorColor),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = CustomTextFieldColors.FocusedBorderColor,
        unfocusedBorderColor = CustomTextFieldColors.UnFocusedBorderColor,
        focusedContainerColor = CustomTextFieldColors.FocusedContainerColor,
        unfocusedContainerColor = CustomTextFieldColors.UnFocusedContainerColor
    ),
    onTextLayout: (TextLayoutResult) -> Unit = {},
    shape: Shape = RoundedCornerShape(10.dp),
    focusedBorderThickness: Dp = 1.dp,
    unfocusedBorderThickness: Dp = 1.dp,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = {
        if (isError) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Field can not be empty",
                color = MaterialTheme.colorScheme.error
            )
        }
    },
    contentPadding: PaddingValues = OutlinedTextFieldDefaults.contentPadding(),
    container: @Composable () -> Unit = {
        OutlinedTextFieldDefaults.ContainerBox(
            enabled = enabled,
            isError = isError,
            interactionSource = interactionSource,
            colors = colors,
            shape = shape,
            focusedBorderThickness = focusedBorderThickness,
            unfocusedBorderThickness = unfocusedBorderThickness
        )
    },
    placeholderText: String,
    placeholder: @Composable (() -> Unit) = {
        Text(
            text = placeholderText,
            style = TextStyle(
                color = CustomTextFieldColors.PlaceHolderTextColor
            )
        )
    }
) {
    BasicTextField(
        modifier = modifier,
        value = value,
        singleLine = singleLine,
        onValueChange = onValueChange,
        readOnly = readOnly,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        minLines = minLines,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        onTextLayout = onTextLayout,
        cursorBrush = cursorBrush
    ) { innerTextField ->
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = innerTextField,
            enabled = enabled,
            singleLine = singleLine,
            interactionSource = interactionSource,
            visualTransformation = visualTransformation,
            placeholder = placeholder,
            container = container,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            isError = isError,
            prefix = prefix,
            suffix = suffix,
            supportingText = supportingText,
            contentPadding = contentPadding
        )
    }
}