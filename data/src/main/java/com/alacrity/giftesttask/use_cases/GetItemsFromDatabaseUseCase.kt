package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.entity.Gif

interface GetItemsFromDatabaseUseCase {

    suspend operator fun invoke(): List<Gif>

}