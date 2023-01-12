package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.entity.Gif

interface RemoveGifFromDatabaseUseCase {

    suspend operator fun invoke(item: Gif)

}