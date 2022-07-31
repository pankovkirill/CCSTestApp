package com.example.ccstestapp.model.interactor

import com.example.ccstestapp.model.room.FavoriteEntity

interface FavoriteInteractor {
    suspend fun getData(): List<FavoriteEntity>
    suspend fun deleteById(id: Int)
}