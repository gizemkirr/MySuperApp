package com.example.feature.rickandmorty.data.model.charactermodel

import com.google.gson.annotations.SerializedName

data class CharacterLocationModel(

    @SerializedName("name")
    var name : String? = null,

    @SerializedName("url")
    var url : String? = null

)
