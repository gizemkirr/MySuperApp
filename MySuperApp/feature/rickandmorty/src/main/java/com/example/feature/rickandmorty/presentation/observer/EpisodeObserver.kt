package com.example.feature.rickandmorty.presentation.observer

import com.example.common.BaseObserver
import com.example.feature.rickandmorty.data.response.EpisodeResponse
import com.example.feature.rickandmorty.presentation.fragments.EpisodeFragmentViewModel

class EpisodeObserver(val viewModel: EpisodeFragmentViewModel) : BaseObserver<EpisodeResponse>() {

    override fun responseSuccess(response: EpisodeResponse) {
        viewModel.setEpisodeData(response)
    }

    override fun responseError(e: Throwable) {
        TODO("Not yet implemented")
    }
}