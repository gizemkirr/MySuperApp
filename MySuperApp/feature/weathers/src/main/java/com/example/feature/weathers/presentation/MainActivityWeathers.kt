package com.example.feature.weathers.presentation

import android.os.Bundle
import com.example.feature.weathers.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseActivity
import com.example.feature.weathers.databinding.ActivityMainWeathersBinding
import com.example.feature.weathers.data.model.Cities
import com.example.feature.weathers.data.model.WeatherResultModel
import com.example.feature.weathers.presentation.recyclerview.RecyclerViewAdapterWeathers
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityWeathers : BaseActivity<MainActivityWeathersViewModel, ActivityMainWeathersBinding>() {

    private val vm : MainActivityWeathersViewModel by viewModels()
    private var baseAdapter : RecyclerViewAdapterWeathers? = null
    var listCity = ArrayList<String>()
    var newCity : String? = null
    var list = ArrayList<WeatherResultModel>()
    var linearLayoutManagerHorizontal = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    var gridLayoutManagerVertical = GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false)

    override fun layoutID(): Int = R.layout.activity_main_weathers
    override fun provideViewModel(): MainActivityWeathersViewModel = vm
    override fun bindViewModel(binding: ActivityMainWeathersBinding) {
        binding.apply {
            viewModel = this@MainActivityWeathers.viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observableWeathers()
        vm.getWeathers(Cities.ANKARA.cityName)
        clickView()
        selectedItem()

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,listCity)
        binding.textInputEditTextWeathers.setAdapter(arrayAdapter)

        binding.textInputEditTextWeathers.setText(arrayAdapter.getItem(0).toString(),false)
    }

    fun observableWeathers(){
        vm.run {
            mainLiveDataWeathers.observe(this@MainActivityWeathers, Observer {
                it.result?.let {
                    list = it
                    adapterCreater()
                }
            })
        }
    }

    fun adapterCreater(){
        baseAdapter = RecyclerViewAdapterWeathers(this,list)
        binding.recyclerViewWeathers.layoutManager = linearLayoutManagerHorizontal
        binding.recyclerViewWeathers.adapter = baseAdapter
    }

    private fun selectedItem(){
        listCity.add(Cities.ANKARA.cityName)
        listCity.add(Cities.ISTANBUL.cityName)
        listCity.add(Cities.IZMIR.cityName)

        binding.textInputEditTextWeathers.setOnItemClickListener(object : AdapterView.OnItemClickListener{
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedItemExposeDropdown(position)
            }
        })

    }

    fun clickView(){
        binding.imageViewClickWeathers.setOnClickListener {
            if (binding.recyclerViewWeathers.layoutManager == linearLayoutManagerHorizontal){
                binding.recyclerViewWeathers.layoutManager = gridLayoutManagerVertical
            }else if (binding.recyclerViewWeathers.layoutManager == gridLayoutManagerVertical){
                binding.recyclerViewWeathers.layoutManager = linearLayoutManagerHorizontal
            }
        }
    }

    fun selectedItemExposeDropdown(position : Int){
        newCity = when(position){
            0 -> Cities.ANKARA.cityName
            1 -> Cities.ISTANBUL.cityName
            2 -> Cities.IZMIR.cityName
            else -> Cities.ANKARA.cityName
        }
        newCity.let {
            vm.getWeathers(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        vm.job?.cancel()
    }

}