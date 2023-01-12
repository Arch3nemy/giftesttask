package com.alacrity.giftesttask.di

import com.alacrity.giftesttask.use_cases.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface UseCaseModule {

    @Binds
    @Singleton
    fun bindNewMessageReceivedUseCase(impl: GetGifsUseCaseImpl): GetGifsUseCase

    @Binds
    @Singleton
    fun bindSaveItemToDatabaseUseCaseImpl(impl: SaveGifToDatabaseUseCaseImpl): SaveGifToDatabaseUseCase

    @Binds
    @Singleton
    fun bindGetItemsFromDatabaseUseCaseImpl(impl: GetGifsFromDatabaseUseCaseImpl): GetGifsFromDBUseCase

    @Binds
    @Singleton
    fun bindRemoveItemFromDatabaseUseCase(impl: RemoveGifFromDatabaseUseCaseImpl): RemoveGifFromDatabaseUseCase


}