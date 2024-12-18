package com.example.feature.news.presentation.fragments

import androidx.lifecycle.MutableLiveData
import com.example.common.BaseFragmentViewModel
import com.example.feature.news.data.response.NewsResponse
import com.example.feature.news.presentation.observer.NewsObserver
import com.example.feature.news.interactor.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsFragmentViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): BaseFragmentViewModel() {

    var newsLiveData = MutableLiveData<NewsResponse>()

    fun getNews(tag : String?, paging : Int?, country : String?){
        newsUseCase.execute(
            NewsObserver(this),
            NewsUseCase.Params(
                tag = tag,
                paging = paging,
                country = country
            )
        )
    }

    fun setNewsData(response: NewsResponse){
        newsLiveData.value = response
    }

    override fun onCleared() {
        super.onCleared()
        newsUseCase.clear()
    }
}