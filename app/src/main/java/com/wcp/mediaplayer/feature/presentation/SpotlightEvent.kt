package com.wcp.mediaplayer.feature.presentation

import com.wcp.mediaplayer.feature.domain.model.Spotlight

sealed interface SpotlightEvent {
    data class ToggleFavourite(val spotlight: Spotlight) : SpotlightEvent
}