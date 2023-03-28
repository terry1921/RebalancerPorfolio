package dev.rockstar.portfolio.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rockstar.portfolio.core.data.repository.*

@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

    @Binds
    fun bindsMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    fun bindsAssetRepository(assetRepositoryImpl: AssetRepositoryImpl): AssetRepository

    @Binds
    fun bindsGroupRepository(groupRepositoryImpl: GroupRepositoryImpl): GroupRepository

}