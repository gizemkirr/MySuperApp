package com.example.feature.weathers.data

import com.example.common.BuildConfig
import com.example.feature.weathers.data.response.WeathersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("content-type: application/json",
             "authorization: apikey ${BuildConfig.API_KEY}")
    @GET("getWeathers")
    suspend fun getWeathers(@Query("data.city") dataCity : String?,
                            @Query("data.lang") dataLang : String?) : Response<WeathersResponse>

}