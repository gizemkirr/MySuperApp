package com.example.feature.rickandmorty.data.model.episodemodel

import com.example.common.BaseResponse
import com.google.gson.annotations.SerializedName

data class EpisodeResultModel(

    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("name")
    var name : String? = null,

    @SerializedName("air_date")
    var air_date : String? = null,

    @SerializedName("episode")
    var episode : String? = null,

    @SerializedName("characters")
    var characters : ArrayList<String>? = null,

    @SerializedName("url")
    var url : String? = null,

    @SerializedName("created")
    var created : String? = null

) : BaseResponse()
