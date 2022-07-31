package com.example.ccstestapp.di

import android.content.Context
import androidx.room.Room
import com.example.ccstestapp.model.room.FavoriteDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Singleton
    @Provides
    fun provideDB(context: Context): FavoriteDataBase =
        Room.databaseBuilder(context, FavoriteDataBase::class.java, "favorite.db").build()
}