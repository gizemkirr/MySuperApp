package com.example.feature.news.interactor

import com.example.common.BaseUseCase
import com.example.feature.news.data.repo.RemoteDataSource
import com.example.feature.news.data.response.NewsResponse
import io.reactivex.Single
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseUseCase<NewsResponse, NewsUseCase.Params>(){

    data class Params(
        var tag : String? = null,
        var paging : Int? = null,
        var country : String? = null
    )

    override fun useCaseFollower(params: Params?): Single<NewsResponse> = remoteDataSource.getNews(
        tag = params?.tag,
        paging = params?.paging,
        country = params?.country
    )

}