package com.example.feature.weathers.data.repo

import com.example.common.BaseRetrofitFactory
import com.example.common.BuildConfig
import com.example.feature.weathers.data.ApiService
import com.example.feature.weathers.data.response.WeathersResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val retrofitFactory: BaseRetrofitFactory
) {

    private val requestCreator by lazy {
        retrofitFactory.getRetrofit(BuildConfig.BASE_URL_WEATHERS).create(ApiService::class.java)
    }

    suspend fun getWeathers(dataCity : String?, dataLang : String?) : Response<WeathersResponse> = requestCreator.getWeathers(dataCity = dataCity, dataLang = dataLang)

}