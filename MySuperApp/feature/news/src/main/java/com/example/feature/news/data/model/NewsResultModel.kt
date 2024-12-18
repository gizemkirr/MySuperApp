package com.example.feature.news.data.model

import com.google.gson.annotations.SerializedName

data class NewsResultModel(

    @SerializedName("key")
    var key : String? = null,

    @SerializedName("url")
    var url : String? = null,

    @SerializedName("description")
    var description : String? = null,

    @SerializedName("image")
    var image : String? = null,

    @SerializedName("name")
    var name : String? = null,

    @SerializedName("source")
    var source : String? = null

)
