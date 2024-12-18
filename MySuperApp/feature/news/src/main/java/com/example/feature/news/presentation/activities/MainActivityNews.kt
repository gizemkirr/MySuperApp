package com.example.feature.news.presentation.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.common.BaseActivity
import com.example.feature.news.R
import com.example.feature.news.databinding.ActivityMainNewsBinding
import com.example.feature.news.data.model.TagList
import com.example.feature.news.presentation.fragments.NewsFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityNews : BaseActivity<MainActivityNewsViewModel, ActivityMainNewsBinding>() {

    private val vm : MainActivityNewsViewModel by viewModels()
    var tagList = ArrayList<String>()
    var newTag : TagList? = null
    var country : String? = null
    var newsFragment : NewsFragment? = null
    var listener : com.example.feature.news.listener.ClickListenerView? = null

    override fun layoutID(): Int = R.layout.activity_main_news
    override fun provideViewModel(): MainActivityNewsViewModel = vm
    override fun bindViewModel(binding: ActivityMainNewsBinding) {
        binding.apply {
            viewModel = this@MainActivityNews.viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayoutNews, NewsFragment(),"newsFragmentTag")
        transaction.commit()

        tagList.add("General")
        tagList.add("Sport")
        tagList.add("Economy")
        tagList.add("Technology")

        var arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,tagList)
        binding.textInputEditTextNews.setAdapter(arrayAdapter)

        newTag = TagList.GENERAL

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.text == "Türkiye"){
                    newTag?.name?.let { newsFragment?.exposeDropdownRequest(it.lowercase(),"tr") }
                }else if(tab?.text == "Almanya"){
                    newTag?.name?.let { newsFragment?.exposeDropdownRequest(it.lowercase(), "de") }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
            override fun onTabReselected(tab: TabLayout.Tab?) = Unit
        })

        binding.textInputEditTextNews.setText(arrayAdapter.getItem(0).toString(),false)

        binding.textInputEditTextNews.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItem(position)
            }
        })

        binding.imageViewClickNews.setOnClickListener {
            listener?.clickView()
        }
    }

    override fun onStart() {
        super.onStart()
        newsFragment = supportFragmentManager.findFragmentByTag("newsFragmentTag") as NewsFragment
    }

    private fun selectedItem(position : Int) {
        newTag = when (position) {
            0 -> {
                TagList.GENERAL
            }

            1 -> {
                TagList.SPORT
            }

            2 -> {
                TagList.ECONOMY
            }

            3 -> {
                TagList.TECHNOLOGY
            }

            else -> TagList.GENERAL
        }

        var selectedTabPosition = binding.tabLayout.selectedTabPosition

        if (selectedTabPosition == 0){
            country = "tr"
        }else if (selectedTabPosition == 1){
            country = "de"
        }

        country?.let { countryName ->
            newTag?.name?.let {
                newsFragment?.exposeDropdownRequest(it.lowercase(), countryName)
            }
        }
    }
}