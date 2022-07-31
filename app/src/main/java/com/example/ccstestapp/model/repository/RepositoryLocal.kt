package com.example.ccstestapp.model.repository

import com.example.ccstestapp.model.room.FavoriteEntity

interface RepositoryLocal {
    suspend fun getData(): List<FavoriteEntity>
    suspend fun saveToDb(entity: FavoriteEntity)
    suspend fun delete(id: Int)
}