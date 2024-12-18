package com.example.feature.news.data.response

import com.example.common.BaseResponse
import com.example.feature.news.data.model.NewsResultModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @SerializedName("result")
    var result : ArrayList<NewsResultModel>? = null

) : BaseResponse()
