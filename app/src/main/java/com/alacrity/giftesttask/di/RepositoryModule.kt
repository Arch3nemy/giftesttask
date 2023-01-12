package com.alacrity.giftesttask.di

import com.alacrity.giftesttask.repository.Repository
import com.alacrity.giftesttask.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: RepositoryImpl): Repository

}