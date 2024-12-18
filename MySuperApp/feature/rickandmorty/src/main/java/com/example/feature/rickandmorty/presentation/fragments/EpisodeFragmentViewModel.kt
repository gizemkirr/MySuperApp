package com.example.feature.rickandmorty.presentation.fragments

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseFragmentViewModel
import com.example.feature.rickandmorty.data.response.EpisodeResponse
import com.example.feature.rickandmorty.presentation.observer.EpisodeObserver
import com.example.feature.rickandmorty.domain.EpisodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeFragmentViewModel @Inject constructor(
    private val episodeUseCase: EpisodeUseCase
) : BaseFragmentViewModel() {

    val episodeLivaData = MutableLiveData<EpisodeResponse>()
    var episodeLivaDataError = MutableLiveData<Unit>()

    fun getEpisode(name : String?, page: Int) {
        episodeUseCase.execute(
            EpisodeObserver(this),
            EpisodeUseCase.Params(
                name = name,
                page = page
            )
        )
    }

    fun setEpisodeData(response: EpisodeResponse){
        episodeLivaData.value = response
    }

    override fun onCleared() {
        super.onCleared()
        episodeUseCase.clear()
    }

}