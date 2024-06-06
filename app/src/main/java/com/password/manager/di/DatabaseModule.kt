package com.password.manager.di

import android.content.Context
import androidx.room.Room
import com.password.manager.data.database.DatabaseHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabaseHelper(@ApplicationContext appContext: Context): DatabaseHelper =
        Room.databaseBuilder(
            appContext,
            DatabaseHelper::class.java, "PasswordDatabase"
        ).build()

    @Provides
    @Singleton
    fun providesProjectDao(databaseHelper: DatabaseHelper) = databaseHelper.getPasswordDao()
}