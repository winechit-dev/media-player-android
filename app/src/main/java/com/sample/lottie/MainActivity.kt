package com.sample.lottie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sample.lottie.ui.theme.LottieAnimationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LottieAnimationTheme {
                // A surface container using the 'background' color from the theme
                var isPlaying by remember { mutableStateOf(false) }
                var showAnimationIcon by remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .combinedClickable(
                            onClick = {},
                            onDoubleClick = {
                                isPlaying = true
                                showAnimationIcon = true
                            }
                        ),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (showAnimationIcon) {
                        Favorite(
                            isPlaying = isPlaying,
                            onAnimationCompleted = {
                                showAnimationIcon = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Favorite(isPlaying: Boolean, onAnimationCompleted: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.favourite_ani))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying,
    )
    if (progress == 1.0f) {
        onAnimationCompleted()
    }

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LottieAnimationTheme {
        Greeting("Android")
    }
}