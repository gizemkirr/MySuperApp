package com.example.feature.rickandmorty.domain

import com.example.common.BaseUseCase
import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel
import com.example.feature.rickandmorty.data.repo.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class CharacterIDUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseUseCase<CharacterResultModel, CharacterIDUseCase.Params>(){

    data class Params(
        var id : Int? = null
    )

    override fun useCaseFollower(params: Params?): Single<CharacterResultModel> = remoteDataSource.getCharactersID(
        id = params?.id
    )

}