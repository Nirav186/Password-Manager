package com.password.manager.ui.features.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.password.manager.R
import com.password.manager.data.model.Password
import com.password.manager.ui.theme.SfProDisplay

object SavedPasswordItemDefaults {
    val Height: Dp = 70.dp
    val Shape: Shape = RoundedCornerShape(35.dp)
    val BorderColor = Color(0xFFEDEDED)
    val BorderWidth = 1.dp
    val BackgroundColor = Color.White
    val HorizontalPadding = 30.dp
}

@Composable
fun SavedPasswordItem(password: Password, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(SavedPasswordItemDefaults.Height)
            .clip(SavedPasswordItemDefaults.Shape)
            .border(
                width = SavedPasswordItemDefaults.BorderWidth,
                color = SavedPasswordItemDefaults.BorderColor,
                shape = SavedPasswordItemDefaults.Shape
            )
            .background(SavedPasswordItemDefaults.BackgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = SavedPasswordItemDefaults.HorizontalPadding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = password.accountName ?: "",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            )
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 6.dp),
            text = "*******",
            style = TextStyle(
                color = Color(0xFFC6C6C6),
                fontSize = 20.sp,
                fontFamily = SfProDisplay,
                fontWeight = FontWeight.W600
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = "Open"
        )
    }
}