package com.alacrity.giftesttask.ui.models

import com.alacrity.giftesttask.BaseEvent
import com.alacrity.giftesttask.entity.Gif
import com.alacrity.giftesttask.ui.MainViewModel

sealed class MainEvent: BaseEvent {

    object EnterScreen : MainEvent()

    object OnBackClicked : MainEvent()

    object EnterSavedGifs : MainEvent()

    data class OnSaveClicked(val gif: Gif?) : MainEvent()

    data class EnterSecondScreen(val index: Int): MainEvent()

    data class OnRemoveClicked(val gif: Gif?) : MainEvent()

}

fun MainViewModel.enterScreen() {
    obtainEvent(MainEvent.EnterScreen)
}

fun MainViewModel.enterSecondScreen(index: Int) {
    obtainEvent(MainEvent.EnterSecondScreen(index))
}

fun MainViewModel.onSaveClick(gif: Gif?) {
    obtainEvent(MainEvent.OnSaveClicked(gif))
}

fun MainViewModel.onBackClick() {
    obtainEvent(MainEvent.OnBackClicked)
}

fun MainViewModel.onRemoveClick(gif: Gif?) {
    obtainEvent(MainEvent.OnRemoveClicked(gif))
}

fun MainViewModel.enterSavedScreen() {
    obtainEvent(MainEvent.EnterSavedGifs)
}