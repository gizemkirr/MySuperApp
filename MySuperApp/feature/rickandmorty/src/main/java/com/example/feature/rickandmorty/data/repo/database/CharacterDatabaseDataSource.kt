package com.example.feature.rickandmorty.data.repo.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterDatabaseDataSource(var cdao : CharacterFavoriteDao) {

    suspend fun getCharacters() : List<CharacterFavoriteModel> =
        withContext(Dispatchers.IO){
            return@withContext cdao.getFavoriteCharacters()
        }

    suspend fun saveCharacter(characterID : Int, characterName : String, characterImage : String) {
        val newCharacter = CharacterFavoriteModel(characterID,characterName,characterImage)
        cdao.saveFavoriteCharacters(newCharacter)
    }

    suspend fun deleteCharacter(characterID : Int){
        val deleteCharacter = CharacterFavoriteModel(characterID,"","")
        cdao.deleteFavoriteCharacters(deleteCharacter)
    }

}