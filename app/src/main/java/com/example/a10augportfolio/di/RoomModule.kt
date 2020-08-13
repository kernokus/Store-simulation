package com.example.a10augportfolio.di

import com.example.a10augportfolio.model.RoomRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton



@Module
@InstallIn(ApplicationComponent::class)
class RoomModule(){
    @Provides
    @Singleton
     fun provideRoomModule(): RoomRepo {
        return RoomRepo()
    }

}
