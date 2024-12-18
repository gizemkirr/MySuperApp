package com.example.feature.rickandmorty.data

import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel
import com.example.feature.rickandmorty.data.response.CharacterResponse
import com.example.feature.rickandmorty.data.response.EpisodeResponse
import com.example.feature.rickandmorty.data.response.LocationResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getCharacters(@Query("name") name : String?,
                      @Query("page") page : Int?) : Single<CharacterResponse>

    @GET("character/{id}")
    fun getCharactersID(@Path("id") id : Int?) : Single<CharacterResultModel>

    @GET("location")
    fun getLocation(@Query("name") name: String?,
                    @Query("page") page : Int?) : Single<LocationResponse>

    @GET("location/{id}")
    fun getLocationID(@Path("id") id : Int?) : Single<LocationResultModel>

    @GET("episode")
    fun getEpisode(@Query("name") name : String?,
                   @Query("page") page : Int?) : Single<EpisodeResponse>

    @GET("episode/{id}")
    fun getEpisodeID(@Path("id") id : Int?) : Single<EpisodeResultModel>

}