package com.example.ccstestapp.model.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("select * from favorite")
    suspend fun all(): List<FavoriteEntity>

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: FavoriteEntity)

    @Query("delete from favorite where id like :id")
    suspend fun deleteById(id: Int)
}