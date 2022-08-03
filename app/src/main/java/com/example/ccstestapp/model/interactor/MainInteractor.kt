package com.example.ccstestapp.model.interactor

import com.example.ccstestapp.model.data.AppState
import com.example.ccstestapp.model.room.FavoriteEntity

interface MainInteractor {
    suspend fun getData(base: String): AppState
    suspend fun saveDataToDb(entity: FavoriteEntity)
}