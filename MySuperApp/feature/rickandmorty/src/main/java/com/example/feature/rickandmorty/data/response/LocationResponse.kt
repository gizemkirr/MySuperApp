package com.example.feature.rickandmorty.data.response

import com.example.common.BaseResponse
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel
import com.google.gson.annotations.SerializedName

data class LocationResponse (

    @SerializedName("results")
    var results : ArrayList<LocationResultModel>? = null

) : BaseResponse()