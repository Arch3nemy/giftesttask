package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.entity.ApiResponse
import com.alacrity.giftesttask.repository.Repository
import javax.inject.Inject

class GetGifsUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetGifsUseCase {

    override suspend fun invoke(query: String, offset: Int, limit: Int): ApiResponse = repository.getGifs(query, offset, limit)

}