package com.wcp.mediaplayer.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.wcp.mediaplayer.ui.theme.DARK_TRANSPARENT
import kotlinx.coroutines.delay

@SuppressLint("PrivateResource")
@Composable
fun ControlIcon(isPlay: Boolean) {

    var showControl by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = isPlay) {
        showControl = true
        delay(1500L)
        showControl = false
    }

    if (showControl) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                painter = painterResource(
                    id = if (!isPlay) {
                        androidx.media3.ui.R.drawable.exo_icon_play
                    } else {
                        androidx.media3.ui.R.drawable.exo_icon_pause
                    }
                ),
                contentDescription = "iconResId",
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(color = DARK_TRANSPARENT),
                tint = Color.White
            )
        }
    }

}


class PlayButtonPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(
            true,
            false
        )

}

@Preview
@Composable
private fun PlayIconPreview(
    @PreviewParameter(PlayButtonPreviewParameterProvider::class) isPlay: Boolean
) {
    Surface {
        ControlIcon(isPlay = isPlay)
    }
}