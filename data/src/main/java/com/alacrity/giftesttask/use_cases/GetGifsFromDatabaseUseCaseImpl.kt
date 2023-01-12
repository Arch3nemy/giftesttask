package com.alacrity.giftesttask.use_cases

import com.alacrity.giftesttask.repository.Repository
import javax.inject.Inject

class GetGifsFromDatabaseUseCaseImpl @Inject constructor(
    private val repository: Repository
): GetGifsFromDBUseCase {

    override suspend fun invoke() = repository.getGifsFromDB()

}