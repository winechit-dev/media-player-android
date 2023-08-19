package com.wcp.mediaplayer.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcp.mediaplayer.feature.data.datasource.DummySpotlightData
import com.wcp.mediaplayer.feature.domain.model.Spotlight
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

@HiltViewModel
class SpotlightViewModel @Inject constructor() : ViewModel() {

    private val _spotlightsState: MutableStateFlow<List<Spotlight>> = MutableStateFlow(emptyList())
    val spotlightsState: StateFlow<List<Spotlight>> = _spotlightsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _spotlightsState.value = DummySpotlightData.spotlight
        }
    }

    fun toggleFavourite(spotlight: Spotlight) {
        viewModelScope.launch(Dispatchers.IO) {
            _spotlightsState.getAndUpdate { list ->
                list.map { if (it.id == spotlight.id) it.copy(isLiked = !spotlight.isLiked) else it }
            }
        }
    }
}