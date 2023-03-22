package dev.rockstar.portfolio.core.preferences.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.rockstar.portfolio.core.preferences.Preferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): Preferences {
        val sharedPreferences = context.getSharedPreferences("balancer_portfolio", MODE_PRIVATE)
        return Preferences(sharedPreferences)
    }

}