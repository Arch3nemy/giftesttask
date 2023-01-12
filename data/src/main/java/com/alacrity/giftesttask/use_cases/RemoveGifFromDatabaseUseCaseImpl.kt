package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.entity.Gif
import com.alacrity.giftesttask.repository.Repository
import javax.inject.Inject

class RemoveGifFromDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
) : RemoveGifFromDatabaseUseCase {

    override suspend fun invoke(item: Gif) = repository.removeGifFromDB(item)

}