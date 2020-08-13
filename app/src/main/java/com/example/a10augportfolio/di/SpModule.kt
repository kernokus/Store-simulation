package com.example.a10augportfolio.di


import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.a10augportfolio.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class SpModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(App.ctx)
    }
}