package com.example.a10augportfolio.di

import android.content.Context
import com.example.a10augportfolio.model.RoomRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    @Provides
    @Singleton
     fun provideRoomModule(@ApplicationContext context: Context): RoomRepo {
        return RoomRepo(context)
    }



}
