package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.entity.Gif

interface GetGifsFromDBUseCase {

    suspend operator fun invoke(): List<Gif>

}