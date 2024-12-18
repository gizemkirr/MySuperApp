package com.example.feature.rickandmorty.presentation.fragments

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseFragmentViewModel
import com.example.feature.rickandmorty.data.response.LocationResponse
import com.example.feature.rickandmorty.presentation.observer.LocationObserver
import com.example.feature.rickandmorty.domain.LocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationFragmentViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase
) : BaseFragmentViewModel() {

    var locationLiveData = MutableLiveData<LocationResponse>()
    var locationLiveDataError = MutableLiveData<Unit>()

    fun getLocation(page : Int, name : String?){
        locationUseCase.execute(
            LocationObserver(this),
            LocationUseCase.Params(
                name = name,
                page = page
            )
        )
    }

    fun setLocationData(response: LocationResponse){
        locationLiveData.value = response
    }

    override fun onCleared() {
        super.onCleared()
        locationUseCase.clear()
    }
}