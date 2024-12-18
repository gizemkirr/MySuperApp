package com.example.feature.rickandmorty.domain

import com.example.common.BaseUseCase
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel
import com.example.feature.rickandmorty.data.repo.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class LocationIDUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseUseCase<LocationResultModel, LocationIDUseCase.Params>(){

    data class Params(
        var id : Int? = null
    )

    override fun useCaseFollower(params: Params?): Single<LocationResultModel> = remoteDataSource.getLocationID(
        id = params?.id
    )

}