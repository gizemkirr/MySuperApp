package com.example.feature.news.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseFragment
import com.example.feature.news.R
import com.example.feature.news.databinding.FragmentNewsBinding
import com.example.feature.news.data.model.NewsResultModel
import com.example.feature.news.presentation.activities.DetailActivityNews
import com.example.feature.news.presentation.activities.MainActivityNews
import com.example.feature.news.presentation.recyclerview.RecyclerViewAdapterNews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : BaseFragment<NewsFragmentViewModel, FragmentNewsBinding>(),
    com.example.feature.news.listener.PaginationListener,
    com.example.feature.news.listener.ClickListenerItem,
    com.example.feature.news.listener.ClickListenerView {

    private val vm : NewsFragmentViewModel by viewModels()
    var baseAdapter : RecyclerViewAdapterNews? = null
    var list = ArrayList<NewsResultModel>()
    var newsType : String? = null
    var newPaging : Int = 0
    var country : String? = "tr"
    var linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    var gridLayoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)

    override fun layoutID(): Int = R.layout.fragment_news
    override fun provideViewModel(): NewsFragmentViewModel = vm
    override fun bindViewModel(binding: FragmentNewsBinding) {
        binding.apply {
            viewModel = this@NewsFragment.viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.let {
            it.getNews(newsType,newPaging,country)
        }
        observableViewModel()

        (activity as MainActivityNews).listener = this
    }

    fun observableViewModel(){
        vm.run {
            newsLiveData.observe(viewLifecycleOwner, Observer {
                if (list.isNullOrEmpty()){
                    it.result?.let { resultData ->
                        list = resultData
                        adapterCreater()
                    }
                }else{
                    it.result?.let {newList ->
                        baseAdapter?.newList(newList)
                    }
                }
            })
        }
    }

    fun adapterCreater(){
        baseAdapter = RecyclerViewAdapterNews(requireContext(),list,this,this)
        binding.recyclerViewNews.layoutManager = linearLayoutManager
        binding.recyclerViewNews.adapter = baseAdapter
    }

    override fun getPage() {
        newPaging += 1
        vm.getNews(newsType,newPaging,country)
    }

    fun exposeDropdownRequest(tag : String, country : String){
        list.clear()
        newPaging = 0
        this.newsType = tag
        this.country = country
        vm.getNews(this.newsType,newPaging,this.country)
    }

    override fun clickItem(position: Int) {
        val intent = Intent(requireContext(), DetailActivityNews::class.java)
        val bundle = Bundle()
        bundle.putString("name",list.get(position).name)
        bundle.putString("description",list.get(position).description)
        bundle.putString("source",list.get(position).source)
        bundle.putString("image",list.get(position).image)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun clickView() {
        if (binding.recyclerViewNews.layoutManager == linearLayoutManager){
            binding.recyclerViewNews.layoutManager = gridLayoutManager
        }else if (binding.recyclerViewNews.layoutManager == gridLayoutManager){
            binding.recyclerViewNews.layoutManager = linearLayoutManager
        }
    }
}