package com.example.feature.rickandmorty.presentation.activities

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.common.BaseActivityViewModel
import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel
import com.example.feature.rickandmorty.presentation.observer.CharacterIDObserver
import com.example.feature.rickandmorty.domain.CharacterIDUseCase
import com.example.feature.rickandmorty.presentation.observer.EpisodeIDObserver
import com.example.feature.rickandmorty.domain.EpisodeIDUseCase
import com.example.feature.rickandmorty.presentation.observer.LocationIDObserver
import com.example.feature.rickandmorty.domain.LocationIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailActivityRAMViewModel @Inject constructor(
    private val characterIDUseCase: CharacterIDUseCase,
    private val locationIDUseCase: LocationIDUseCase,
    private val episodeIDUseCase: EpisodeIDUseCase
) : BaseActivityViewModel() {

    var detailLiveDataCharacter = MutableLiveData<CharacterResultModel>()
    var detailLiveDataLocation = MutableLiveData<LocationResultModel>()
    var detailLiveDataEpisode = MutableLiveData<EpisodeResultModel>()
    var firstObservable = ObservableField<String>()
    var secondObservable = ObservableField<String>()
    var thirdObservable = ObservableField<String>()
    var fourthObservable = ObservableField<String>()
    var fifthObservable = ObservableField<String>()
    var sixthObservable = ObservableField<String>()

    fun getCharacterID(id : Int?){
        characterIDUseCase.execute(
            CharacterIDObserver(this),
            CharacterIDUseCase.Params(
                id = id
            )
        )
    }

    fun setCharacterIDData(response : CharacterResultModel){
        detailLiveDataCharacter.value = response
    }

    fun getLocationID(id : Int?){
        locationIDUseCase.execute(
            LocationIDObserver(this),
            LocationIDUseCase.Params(
                id = id
            )
        )
    }

    fun setLocationIDData(response : LocationResultModel){
        detailLiveDataLocation.value = response
    }

    fun getEpisodeID(id : Int?){
        episodeIDUseCase.execute(
            EpisodeIDObserver(this),
            EpisodeIDUseCase.Params(
                id = id
            )
        )
    }

    fun setEpisodeIDData(response: EpisodeResultModel){
        detailLiveDataEpisode.value = response
    }

    override fun onCleared() {
        super.onCleared()
        characterIDUseCase.clear()
    }

}