package com.example.feature.rickandmorty.presentation.fragments

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseFragmentViewModel
import com.example.feature.rickandmorty.data.repo.database.CharacterFavoriteModel
import com.example.feature.rickandmorty.data.repo.database.CharacterRepository
import com.example.feature.rickandmorty.data.response.CharacterResponse
import com.example.feature.rickandmorty.presentation.observer.CharacterObserver
import com.example.feature.rickandmorty.domain.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterFragmentViewModel @Inject constructor(
    var crepo : CharacterRepository,
    private val characterUseCase: CharacterUseCase
) : BaseFragmentViewModel() {

    var characterLiveData = MutableLiveData<CharacterResponse>()
    var favoriteCharacterLiveData = MutableLiveData<List<CharacterFavoriteModel>>()
    var characterLiveDataError = MutableLiveData<Unit>()

    fun getCharacter(page : Int, name : String?){
        characterUseCase.execute(
            CharacterObserver(this),
            CharacterUseCase.Params(
                name = name,
                page = page
            )
        )
    }

    fun setCharacterData(response : CharacterResponse){
        characterLiveData.value = response
    }

    fun getFavoriteCharacters(){
        CoroutineScope(Dispatchers.Main).launch {
            favoriteCharacterLiveData.value = crepo.getCharacters()
        }
    }

    override fun onCleared() {
        super.onCleared()
        characterUseCase.clear()
    }
}