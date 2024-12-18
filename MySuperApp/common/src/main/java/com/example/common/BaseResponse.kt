package com.example.common

import com.example.common.model.InfoModel
import com.google.gson.annotations.SerializedName

open class BaseResponse {

    @SerializedName("info")
    var info : InfoModel? = null

    @SerializedName("success")
    var success : Boolean? = null

}