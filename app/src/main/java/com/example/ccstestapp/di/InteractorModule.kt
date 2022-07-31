package com.example.ccstestapp.di

import com.example.ccstestapp.model.interactor.FavoriteInteractor
import com.example.ccstestapp.model.interactor.FavoriteInteractorImpl
import com.example.ccstestapp.model.interactor.MainInteractor
import com.example.ccstestapp.model.interactor.MainInteractorImpl
import com.example.ccstestapp.model.repository.RepositoryLocal
import com.example.ccstestapp.model.repository.RepositoryRemote
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
class InteractorModule {
    @Provides
    @Reusable
    fun provideMainInteractor(
        repositoryRemote: RepositoryRemote,
        repositoryLocal: RepositoryLocal
    ): MainInteractor = MainInteractorImpl(repositoryRemote, repositoryLocal)

    @Provides
    @Reusable
    fun provideFavoriteInteractor(repositoryLocal: RepositoryLocal): FavoriteInteractor =
        FavoriteInteractorImpl(repositoryLocal)
}