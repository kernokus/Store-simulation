package com.example.a10augportfolio.di

import com.example.a10augportfolio.MainActivity
import com.example.a10augportfolio.model.NetworkRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetwModule {
        @Provides
        @Singleton
        fun provideNetworkModule(): NetworkRepo {
            return NetworkRepo()
        }
    }
