package com.example.feature.news.presentation.activities

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.common.BaseActivityViewModel
import com.example.feature.news.data.model.NewsResultModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailActivityNewsViewModel @Inject constructor() : BaseActivityViewModel() {

    var detailLiveDataNews = MutableLiveData<NewsResultModel>()
    var firstObservable = ObservableField<String>()
    var secondObservable = ObservableField<String>()
    var thirdObservable = ObservableField<String>()
    var fourthObservable = ObservableField<String>()

    fun liveData(dataImage : String?, dataName : String?, dataDescription : String?, dataSource : String?) {
        var model = NewsResultModel(image = dataImage, name = dataName, description = dataDescription, source = dataSource)
        detailLiveDataNews.value = model
    }
}