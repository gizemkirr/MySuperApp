package com.example.feature.rickandmorty.domain

import com.example.common.BaseUseCase
import com.example.feature.rickandmorty.data.repo.RemoteDataSource
import com.example.feature.rickandmorty.data.response.CharacterResponse
import io.reactivex.Single
import javax.inject.Inject

class CharacterUseCase @Inject constructor(
    private val remoteDataSource : RemoteDataSource
) : BaseUseCase<CharacterResponse, CharacterUseCase.Params>() {

    data class Params(
        var name : String? = null,
        var page : Int? = null
    )

    override fun useCaseFollower(params: Params?): Single<CharacterResponse> = remoteDataSource.getCharacters(
        name = params?.name,
        page = params?.page
    )

}