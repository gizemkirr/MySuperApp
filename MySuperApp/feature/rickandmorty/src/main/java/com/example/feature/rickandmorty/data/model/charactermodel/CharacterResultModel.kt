package com.example.feature.rickandmorty.data.model.charactermodel

import com.example.common.BaseResponse
import com.google.gson.annotations.SerializedName

data class CharacterResultModel(

    @SerializedName("id")
    var id : Int? = null,

    @SerializedName("name")
    var name : String? = null,

    @SerializedName("status")
    var status : String? = null,

    @SerializedName("species")
    var species : String? = null,

    @SerializedName("type")
    var type : String? = null,

    @SerializedName("gender")
    var gender : String? = null,

    @SerializedName("origin")
    var origin : CharacterOriginModel? = null,

    @SerializedName("location")
    var location : CharacterLocationModel? = null,

    @SerializedName("image")
    var image : String? = null,

    @SerializedName("episode")
    var episode : ArrayList<String>? = null,

    @SerializedName("url")
    var url : String? = null,

    @SerializedName("created")
    var created : String? = null

) : BaseResponse()
