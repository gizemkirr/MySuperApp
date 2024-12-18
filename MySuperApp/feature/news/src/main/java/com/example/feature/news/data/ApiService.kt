package com.example.feature.news.data

import com.example.common.BuildConfig
import com.example.feature.news.data.response.NewsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Content-Type: application/json",
             "Authorization: apikey ${BuildConfig.API_KEY}")
    @GET("getNews")
    fun getNews(@Query("newsType") tag : String?,
                @Query("paging") paging : Int?,
                @Query("country") country : String?) : Single<NewsResponse>

}