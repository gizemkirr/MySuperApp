package com.example.feature.rickandmorty.presentation.observer

import com.example.common.BaseObserver
import com.example.feature.rickandmorty.data.response.CharacterResponse
import com.example.feature.rickandmorty.presentation.fragments.CharacterFragmentViewModel

class CharacterObserver(var viewModel: CharacterFragmentViewModel): BaseObserver<CharacterResponse>() {

    override fun responseSuccess(response: CharacterResponse) {
        viewModel.setCharacterData(response)
    }

    override fun responseError(e: Throwable) {

    }
}