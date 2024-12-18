package com.example.feature.weathers.data.response

import com.example.common.BaseResponse
import com.example.feature.weathers.data.model.WeatherResultModel
import com.google.gson.annotations.SerializedName

data class WeathersResponse(

    @SerializedName("result")
    var result : ArrayList<WeatherResultModel>? = null

) : BaseResponse()
