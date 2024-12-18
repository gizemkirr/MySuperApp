package com.example.feature.rickandmorty.presentation.observer

import com.example.common.BaseObserver
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAMViewModel

class LocationIDObserver(var viewModel: DetailActivityRAMViewModel) : BaseObserver<LocationResultModel>(){

    override fun responseSuccess(response: LocationResultModel) {
        viewModel.setLocationIDData(response)
    }

    override fun responseError(e: Throwable) {
        TODO("Not yet implemented")
    }

}