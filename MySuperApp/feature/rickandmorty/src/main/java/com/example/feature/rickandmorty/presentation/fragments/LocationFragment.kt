package com.example.feature.rickandmorty.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseFragment
import com.example.common.model.InfoModel
import com.example.feature.rickandmorty.R
import com.example.feature.rickandmorty.databinding.FragmentLocationBinding
import com.example.feature.rickandmorty.listener.ClickListener
import com.example.feature.rickandmorty.listener.PaginationListener
import com.example.feature.rickandmorty.data.model.locationmodel.LocationResultModel
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAM
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAM.Companion.LOCATION_ID
import com.example.feature.rickandmorty.presentation.activities.MainActivityRAM
import com.example.feature.rickandmorty.presentation.recyclerview.RecyclerViewAdapterLocation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationFragment : BaseFragment<LocationFragmentViewModel, FragmentLocationBinding>(),
    PaginationListener, ClickListener {

    private val vm : LocationFragmentViewModel by viewModels()
    var linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    var gridLayoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
    private var baseAdapter : RecyclerViewAdapterLocation? = null
    var list = ArrayList<LocationResultModel>()
    var info : InfoModel? = null
    var name : String? = null
    var page = 1

    override fun layoutID(): Int = R.layout.fragment_location
    override fun provideViewModel(): LocationFragmentViewModel = vm
    override fun bindViewModel(binding: FragmentLocationBinding) {
        binding.apply {
            viewModel = this@LocationFragment.viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observableViewModel()
        vm.getLocation(page,name)

        binding.searchViewLocation.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchLocation(query)
                vm.getLocation(page, query)
                list.clear()
                (activity as MainActivityRAM).hideKeyboard()
                binding.searchViewLocation.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    fun searchLocation(searchKey : String?){
        if (searchKey != null) {
            Log.e("Kişi Ara", searchKey)
        }
    }

    fun observableViewModel(){
        vm.run {
            locationLiveData.observe(viewLifecycleOwner, Observer {
                it.info?.let {
                    info = it
                }
                if (list.isNullOrEmpty()){
                    it.results?.let { resultData ->
                        list = resultData
                        adapterCreater()
                    }
                }else{
                    it.results?.let {
                        baseAdapter?.newList(it)
                    }
                }
            })
            locationLiveDataError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context,"Aradığınız konum bulunamadı... Tekrar seçim yapınız.",Toast.LENGTH_SHORT)
                    .show()
                binding.searchViewLocation.setQuery("",true)
            })
        }
    }

    fun adapterCreater(){
        binding.imageViewClickLocation.setOnClickListener {
            if(binding.recyclerViewLocation.layoutManager == linearLayoutManager){
                binding.recyclerViewLocation.layoutManager = gridLayoutManager
            }else if(binding.recyclerViewLocation.layoutManager == gridLayoutManager){
                binding.recyclerViewLocation.layoutManager = linearLayoutManager }
        }
        baseAdapter = RecyclerViewAdapterLocation(requireContext(),list,this,this)
        binding.recyclerViewLocation.layoutManager = linearLayoutManager
        binding.recyclerViewLocation.adapter = baseAdapter
    }

    override fun getPage() {
        info?.next?.split("=")?.get(1)?.toInt()?.let {
            page = it
            vm.getLocation(page,name)
        }
    }

    override fun click(id: Int?) {
        val intent = Intent(requireContext(), DetailActivityRAM::class.java)
        intent.putExtra(LOCATION_ID,id)
        startActivity(intent)
    }

}