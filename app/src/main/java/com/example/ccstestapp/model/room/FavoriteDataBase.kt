package com.example.ccstestapp.model.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}