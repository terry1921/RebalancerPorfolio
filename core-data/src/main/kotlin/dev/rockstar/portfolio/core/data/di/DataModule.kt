package dev.rockstar.portfolio.core.data.di

import dev.rockstar.portfolio.core.data.repository.MainRepository
import dev.rockstar.portfolio.core.data.repository.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rockstar.portfolio.core.data.repository.AssetRepository
import dev.rockstar.portfolio.core.data.repository.AssetRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    fun bindsAssetRepository(assetRepositoryImpl: AssetRepositoryImpl): AssetRepository

}