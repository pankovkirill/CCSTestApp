package com.example.ccstestapp.model.repository

import com.example.ccstestapp.model.room.FavoriteDataBase
import com.example.ccstestapp.model.room.FavoriteEntity
import javax.inject.Inject

class RepositoryLocalImpl
@Inject constructor(
    private val favoriteDataBase: FavoriteDataBase
) : RepositoryLocal {
    override suspend fun getData(): List<FavoriteEntity> {
        return favoriteDataBase.favoriteDao().all()
    }

    override suspend fun saveToDb(entity: FavoriteEntity) {
        favoriteDataBase.favoriteDao().insert(entity)
    }

    override suspend fun delete(id: Int) {
        favoriteDataBase.favoriteDao().deleteById(id)
    }
}