package com.example.feature.weathers.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResultModel (

    @SerializedName("date")
    var date : String? = null,

    @SerializedName("day")
    var day : String? = null,

    @SerializedName("icon")
    var icon : String? = null,

    @SerializedName("description")
    var description : String? = null,

    @SerializedName("status")
    var status : String? = null,

    @SerializedName("degree")
    var degree : String? = null,

    @SerializedName("min")
    var min : String? = null,

    @SerializedName("max")
    var max : String? = null,

    @SerializedName("night")
    var night : String? = null,

    @SerializedName("humidity")
    var humidity : String? = null

)