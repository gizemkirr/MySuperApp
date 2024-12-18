package com.example.feature.rickandmorty.data.response

import com.example.common.BaseResponse
import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel
import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @SerializedName("results")
    var results : ArrayList<CharacterResultModel>? = null

) : BaseResponse()
