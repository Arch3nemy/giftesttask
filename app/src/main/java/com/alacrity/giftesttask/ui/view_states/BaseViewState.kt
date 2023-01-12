package com.alacrity.giftesttask.ui.view_states

sealed interface BaseViewState {

    fun getBaseState(): BaseViewState = Loading

    companion object {
        object Loading : BaseViewState
    }
}