package com.example.feature.news.data.response

import com.google.gson.annotations.SerializedName

open class BaseResponse (

    @SerializedName("success")
    var success : Boolean? = null

)