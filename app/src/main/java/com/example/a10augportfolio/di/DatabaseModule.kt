package com.example.a10augportfolio.di

import android.content.Context
import androidx.room.Room
import com.example.a10augportfolio.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(context, AppDatabase::class.java, "db").build()

    @Provides
    @Singleton
    fun provideCatalogDao(appDatabase: AppDatabase) = appDatabase.itemCatalogsDao()

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()
}