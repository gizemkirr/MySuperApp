package com.example.feature.rickandmorty.presentation.observer

import com.example.common.BaseObserver
import com.example.feature.rickandmorty.data.response.LocationResponse
import com.example.feature.rickandmorty.presentation.fragments.LocationFragmentViewModel

class LocationObserver(val viewModel: LocationFragmentViewModel) : BaseObserver<LocationResponse>() {

    override fun responseSuccess(response: LocationResponse) {
        viewModel.setLocationData(response)
    }

    override fun responseError(e: Throwable) {

    }

}