package dev.rockstar.portfolio.core.database.di

import android.app.Application
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rockstar.portfolio.core.database.AssetDao
import dev.rockstar.portfolio.core.database.GroupDao
import dev.rockstar.portfolio.core.database.PortfolioDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): PortfolioDatabase {
        return Room
            .databaseBuilder(application, PortfolioDatabase::class.java, "Portfolio.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideAssetDao(appDatabase: PortfolioDatabase): AssetDao = appDatabase.assetDao()

    @Provides
    @Singleton
    fun provideGroupDao(appDatabase: PortfolioDatabase): GroupDao = appDatabase.groupDao()


}