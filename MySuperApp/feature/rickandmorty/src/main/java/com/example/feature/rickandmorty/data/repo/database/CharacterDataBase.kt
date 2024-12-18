package com.example.feature.rickandmorty.data.repo.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharacterFavoriteModel::class], version = 1)
abstract class CharacterDataBase : RoomDatabase() {

    abstract fun getCharacterDao() : CharacterFavoriteDao

}