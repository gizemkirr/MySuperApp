package com.example.feature.rickandmorty.domain

import com.example.common.BaseUseCase
import com.example.feature.rickandmorty.data.repo.RemoteDataSource
import com.example.feature.rickandmorty.data.response.EpisodeResponse
import io.reactivex.Single
import javax.inject.Inject

class EpisodeUseCase @Inject constructor(
    private val remoteDataSource : RemoteDataSource
) : BaseUseCase<EpisodeResponse, EpisodeUseCase.Params>(){

    data class Params(
        var name : String? = null,
        var page : Int? = null
    )

    override fun useCaseFollower(params: Params?): Single<EpisodeResponse> = remoteDataSource.getEpisode(
        name = params?.name,
        page = params?.page
        )

}