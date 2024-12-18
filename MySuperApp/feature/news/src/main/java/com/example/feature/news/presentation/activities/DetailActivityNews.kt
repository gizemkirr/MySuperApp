package com.example.feature.news.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.common.BaseActivity
import com.example.feature.news.R
import com.example.feature.news.databinding.ActivityDetailNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivityNews : BaseActivity<DetailActivityNewsViewModel, ActivityDetailNewsBinding>() {

    private val vm : DetailActivityNewsViewModel by viewModels()
    var dataImage : String? = null
    var dataName : String? = null
    var dataDescription : String? = null
    var dataSource : String? = null

    override fun layoutID(): Int = R.layout.activity_detail_news
    override fun provideViewModel(): DetailActivityNewsViewModel = vm
    override fun bindViewModel(binding: ActivityDetailNewsBinding) {
        binding.apply {
            viewModel = this@DetailActivityNews.viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observableNews()

        intent.extras?.let {
            dataImage = it.getString("image")
            dataName = it.getString("name")
            dataDescription = it.getString("description")
            dataSource = it.getString("source")
        }
        vm.liveData(dataImage,dataName,dataDescription,dataSource)
    }

    fun observableNews(){
        vm.run {
            detailLiveDataNews.observe(this@DetailActivityNews, Observer {
                firstObservable.set(it?.image)
                secondObservable.set(it?.name)
                thirdObservable.set(it?.description)
                fourthObservable.set(it?.source)
            })
        }
    }
}