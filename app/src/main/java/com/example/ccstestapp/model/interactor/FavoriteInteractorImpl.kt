package com.example.ccstestapp.model.interactor

import com.example.ccstestapp.model.repository.RepositoryLocal
import com.example.ccstestapp.model.room.FavoriteEntity
import javax.inject.Inject

class FavoriteInteractorImpl
@Inject constructor(
    private val repository: RepositoryLocal
) : FavoriteInteractor {
    override suspend fun getData(): List<FavoriteEntity> {
        return repository.getData()
    }

    override suspend fun deleteById(id: Int) {
        repository.delete(id)
    }
}