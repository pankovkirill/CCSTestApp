package com.example.ccstestapp.di

import com.example.ccstestapp.model.data.api.ApiService
import com.example.ccstestapp.model.repository.RepositoryLocal
import com.example.ccstestapp.model.repository.RepositoryLocalImpl
import com.example.ccstestapp.model.repository.RepositoryRemote
import com.example.ccstestapp.model.repository.RepositoryRemoteImpl
import com.example.ccstestapp.model.room.FavoriteDataBase
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class RepositoryModule {
    @Reusable
    @Provides
    fun provideRepositoryLocal(db: FavoriteDataBase): RepositoryLocal = RepositoryLocalImpl(db)

    @Reusable
    @Provides
    fun provideRepositoryRemote(api: ApiService): RepositoryRemote = RepositoryRemoteImpl(api)
}