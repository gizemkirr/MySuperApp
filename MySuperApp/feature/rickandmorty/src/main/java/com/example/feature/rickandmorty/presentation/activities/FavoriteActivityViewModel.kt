package com.example.feature.rickandmorty.presentation.activities

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseActivityViewModel
import com.example.feature.rickandmorty.data.repo.database.CharacterFavoriteModel
import com.example.feature.rickandmorty.data.repo.database.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteActivityViewModel @Inject constructor(
    var crepo : CharacterRepository
) : BaseActivityViewModel(){

    val characterFavoriteLiveData = MutableLiveData<List<CharacterFavoriteModel>>()

    fun getCharacters(){
        CoroutineScope(Dispatchers.Main).launch {
            characterFavoriteLiveData.value = crepo.getCharacters()
        }
    }

}