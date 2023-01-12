package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.entity.ApiResponse

interface GetGifsUseCase {

    suspend operator fun invoke(query: String, offset: Int, limit: Int): ApiResponse

}