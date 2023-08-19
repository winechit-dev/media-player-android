package com.wcp.mediaplayer.feature.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wcp.mediaplayer.component.FavoriteAnimatedIcon
import com.wcp.mediaplayer.component.ControlIcon
import com.wcp.mediaplayer.component.UserAction
import com.wcp.mediaplayer.component.VideoPlayer
import com.wcp.mediaplayer.feature.domain.model.Spotlight

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SpotlightScreen() {
    val viewModel: SpotlightViewModel = hiltViewModel()
    val spotlights by viewModel.spotlightsState.collectAsState()

    var showAnimationIcon by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        VerticalPager(
            pageCount = spotlights.size,
            state = pagerState,
            beyondBoundsPageCount = spotlights.size,
        ) { page ->
            val spotlight = spotlights[page]
            var isPlay by remember { mutableStateOf(true) }

            LaunchedEffect(key1 = pagerState.currentPage){
                isPlay = page == pagerState.currentPage
            }

            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                VideoPlayer(
                    uri = spotlight.getVideoUrl(),
                    isPlay = isPlay,
                    modifier = Modifier.combinedClickable(
                        onClick = {
                            isPlay = !isPlay
                        },
                        onDoubleClick = {
                            showAnimationIcon = true
                            viewModel.toggleFavourite(spotlight)
                        }
                    )
                )

                FooterUserAction(
                    modifier = Modifier.windowInsetsPadding(WindowInsets.navigationBars),
                    spotlight = spotlight,
                    onEvent = {
                        when (it) {
                            is SpotlightEvent.ToggleFavourite -> {
                                viewModel.toggleFavourite(it.spotlight)
                            }
                        }
                    }
                )
                if (showAnimationIcon) {
                    FavoriteAnimatedIcon(
                        enabled = true,
                        onAnimationCompleted = {
                            showAnimationIcon = false
                        }
                    )
                }
                ControlIcon(isPlay = isPlay)
            }
        }
    }
}

@Composable
fun FooterUserAction(
    modifier: Modifier = Modifier,
    spotlight: Spotlight,
    onEvent: (SpotlightEvent) -> Unit
) {
    val iconResId = if (spotlight.isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom
    ) {
        UserAction(
            icon = iconResId,
            onClick = {
                onEvent(SpotlightEvent.ToggleFavourite(spotlight))
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        UserAction(
            icon = Icons.Default.Send,
        )
        Spacer(modifier = Modifier.height(10.dp))
        UserAction(
            icon = Icons.Default.MoreVert,
        )
    }
}

