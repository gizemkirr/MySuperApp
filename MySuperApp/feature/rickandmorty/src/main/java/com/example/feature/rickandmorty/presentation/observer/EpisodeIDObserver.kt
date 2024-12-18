package com.example.feature.rickandmorty.presentation.observer

import com.example.common.BaseObserver
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAMViewModel

class EpisodeIDObserver(var viewModel : DetailActivityRAMViewModel) : BaseObserver<EpisodeResultModel>(){

    override fun responseSuccess(response: EpisodeResultModel) {
        viewModel.setEpisodeIDData(response)
    }

    override fun responseError(e: Throwable) {
        TODO("Not yet implemented")
    }

}