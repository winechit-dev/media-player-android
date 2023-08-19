package com.wcp.mediaplayer.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sample.lottie.R

@Composable
fun FavoriteAnimatedIcon(enabled: Boolean, onAnimationCompleted: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.favourite_ani))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = enabled,
    )
    if (progress == 1.0f) {
        onAnimationCompleted()
    }

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}