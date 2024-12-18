package com.example.feature.rickandmorty.data.response

import com.example.common.BaseResponse
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel
import com.google.gson.annotations.SerializedName

data class EpisodeResponse(

    @SerializedName("results")
    var results : ArrayList<EpisodeResultModel>? = null

) : BaseResponse()
