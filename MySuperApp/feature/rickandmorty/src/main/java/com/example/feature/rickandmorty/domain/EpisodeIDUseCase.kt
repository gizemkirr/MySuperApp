package com.example.feature.rickandmorty.domain

import com.example.common.BaseUseCase
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel
import com.example.feature.rickandmorty.data.repo.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class EpisodeIDUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseUseCase<EpisodeResultModel, EpisodeIDUseCase.Params>(){

    data class Params(
        var id : Int? = null
    )

    override fun useCaseFollower(params: Params?): Single<EpisodeResultModel> = remoteDataSource.getEpisodeID(
        id = params?.id
    )

}