package com.wcp.mediaplayer.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.wcp.mediaplayer.ui.theme.DARK_TRANSPARENT

@Composable
fun UserAction(
    icon: ImageVector,
    onClick: () -> Unit = {}
) {
    Icon(
        imageVector = icon,
        tint = Color.White,
        modifier = Modifier
            .clip(CircleShape)
            .background(color = DARK_TRANSPARENT)
            .clickable { onClick() }
            .padding(10.dp)
            .size(28.dp),
        contentDescription = null
    )
}
