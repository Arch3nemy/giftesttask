package com.alacrity.giftesttask.ui.view_states

import com.alacrity.giftesttask.entity.Gif

sealed class MainViewState: BaseViewState {
    object Loading : MainViewState()
    data class Error(val exception: Throwable? = null, val message: String = "") : MainViewState()
    object FirstScreen : MainViewState()
    data class SavedGifs(val gifs: List<Gif>) : MainViewState()
    data class SecondScreen(val currentGifIndex: Int) : MainViewState()
}