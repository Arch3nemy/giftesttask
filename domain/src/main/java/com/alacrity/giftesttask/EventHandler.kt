package com.alacrity.giftesttask

interface EventHandler<T> {
    fun obtainEvent(event: T)
}