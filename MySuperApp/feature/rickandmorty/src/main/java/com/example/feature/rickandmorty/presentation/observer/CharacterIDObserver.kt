package com.example.feature.rickandmorty.presentation.observer

import com.example.common.BaseObserver
import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAMViewModel

class CharacterIDObserver(var viewModel: DetailActivityRAMViewModel) : BaseObserver<CharacterResultModel>(){

    override fun responseSuccess(response: CharacterResultModel) {
        viewModel.setCharacterIDData(response)
    }

    override fun responseError(e: Throwable) {
        TODO("Not yet implemented")
    }
}