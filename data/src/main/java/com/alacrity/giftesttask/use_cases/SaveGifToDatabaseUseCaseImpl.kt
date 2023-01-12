package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.entity.Gif
import com.alacrity.giftesttask.repository.Repository
import javax.inject.Inject

class SaveGifToDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): SaveGifToDatabaseUseCase {

    override suspend fun invoke(item: Gif) = repository.saveItemToDB(item)

}