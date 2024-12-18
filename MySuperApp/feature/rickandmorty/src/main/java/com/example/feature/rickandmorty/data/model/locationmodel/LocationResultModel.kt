package com.example.feature.rickandmorty.data.model.locationmodel

import com.example.common.BaseResponse
import com.google.gson.annotations.SerializedName

data class LocationResultModel(

    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("name")
    var name : String? = null,

    @SerializedName("type")
    var type : String? = null,

    @SerializedName("dimension")
    var dimension : String? = null,

    @SerializedName("residents")
    var residents : ArrayList<String>? = null,

    @SerializedName("url")
    var url : String? = null,

    @SerializedName("created")
    var created : String? = null

) : BaseResponse()
