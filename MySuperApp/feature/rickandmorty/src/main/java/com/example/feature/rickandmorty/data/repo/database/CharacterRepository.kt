package com.example.feature.rickandmorty.data.repo.database

class CharacterRepository(var cds : CharacterDatabaseDataSource) {

    suspend fun getCharacters() = cds.getCharacters()

    suspend fun saveCharacter(characterID : Int, characterName : String, characterImage : String) = cds.saveCharacter(characterID,characterName,characterImage)

    suspend fun deleteCharacter(characterID : Int) = cds.deleteCharacter(characterID)

}