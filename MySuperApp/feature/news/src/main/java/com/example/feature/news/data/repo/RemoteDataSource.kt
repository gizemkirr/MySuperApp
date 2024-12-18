package com.example.feature.news.data.repo

import com.example.common.BaseRetrofitFactory
import com.example.common.BuildConfig
import com.example.feature.news.data.ApiService
import com.example.feature.news.data.response.NewsResponse
import io.reactivex.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val retrofitFactory: BaseRetrofitFactory
){

    private val requestCreator by lazy {
        retrofitFactory.getRetrofit(BuildConfig.BASE_URL_NEWS).create(ApiService::class.java)
    }

    fun getNews(tag : String?, paging : Int?, country : String?) : Single<NewsResponse> = requestCreator.getNews(tag = tag, paging = paging, country = country)

}