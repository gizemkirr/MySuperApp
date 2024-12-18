package com.example.feature.rickandmorty.data.repo

import com.example.common.BaseRetrofitFactory
import com.example.common.BuildConfig
import com.example.feature.rickandmorty.data.ApiService
import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel
import com.example.feature.rickandmorty.data.response.CharacterResponse
import com.example.feature.rickandmorty.data.response.EpisodeResponse
import com.example.feature.rickandmorty.data.response.LocationResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val retrofitFactory: BaseRetrofitFactory
) {

    private val requestCreator by lazy {
        retrofitFactory.getRetrofit(BuildConfig.BASE_URL_RAM).create(ApiService::class.java)
    }

    fun getCharacters(name : String?, page : Int?) : Single<CharacterResponse> = requestCreator.getCharacters(name = name, page = page)
    fun getCharactersID(id : Int?): Single<CharacterResultModel> = requestCreator.getCharactersID(id = id)

    fun getLocation(name : String?, page : Int?) : Single<LocationResponse> = requestCreator.getLocation(name = name, page = page)
    fun getLocationID(id : Int?) : Single<LocationResultModel> = requestCreator.getLocationID(id = id)

    fun getEpisode(name : String?, page : Int?) : Single<EpisodeResponse> = requestCreator.getEpisode(name = name, page = page)
    fun getEpisodeID(id : Int?) : Single<EpisodeResultModel> = requestCreator.getEpisodeID(id = id)

}