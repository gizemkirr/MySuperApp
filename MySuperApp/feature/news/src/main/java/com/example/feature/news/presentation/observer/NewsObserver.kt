package com.example.feature.news.presentation.observer

import android.util.Log
import com.example.common.BaseObserver
import com.example.feature.news.data.response.NewsResponse
import com.example.feature.news.presentation.fragments.NewsFragmentViewModel

class NewsObserver(var viewModel : NewsFragmentViewModel)
    : BaseObserver<NewsResponse>() {

    override fun responseSuccess(response: NewsResponse) {
        viewModel.setNewsData(response)
    }

    override fun responseError(e: Throwable) {
        Log.e("News Error","News Response çalışmadı.")
    }
}