package com.example.feature.rickandmorty.domain

import com.example.common.BaseUseCase
import com.example.feature.rickandmorty.data.repo.RemoteDataSource
import com.example.feature.rickandmorty.data.response.LocationResponse
import io.reactivex.Single
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseUseCase<LocationResponse, LocationUseCase.Params>(){

    data class Params(
        var name : String? = null,
        var page : Int? = null
    )

    override fun useCaseFollower(params: Params?): Single<LocationResponse> = remoteDataSource.getLocation(
        name = params?.name,
        page = params?.page
    )
}