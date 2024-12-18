package com.example.feature.rickandmorty.data.repo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterFavoriteDao {

    @Query("SELECT * FROM characters")
    suspend fun getFavoriteCharacters() : List<CharacterFavoriteModel>

    @Insert
    suspend fun saveFavoriteCharacters(character : CharacterFavoriteModel)

    @Delete
    suspend fun deleteFavoriteCharacters(character: CharacterFavoriteModel)
}