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
import com.example.feature.rickandmorty.databinding.FragmentEpisodeBinding
import com.example.feature.rickandmorty.listener.ClickListener
import com.example.feature.rickandmorty.listener.PaginationListener
import com.example.feature.rickandmorty.data.model.episodemodel.EpisodeResultModel
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAM
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAM.Companion.EPISODE_ID
import com.example.feature.rickandmorty.presentation.activities.MainActivityRAM
import com.example.feature.rickandmorty.presentation.recyclerview.RecyclerViewAdapterEpisode
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeFragment : BaseFragment<EpisodeFragmentViewModel, FragmentEpisodeBinding>(),
    PaginationListener, ClickListener {

    private val vm : EpisodeFragmentViewModel by viewModels()
    var linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    var gridLayoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
    private var baseAdapter : RecyclerViewAdapterEpisode? = null
    var list = ArrayList<EpisodeResultModel>()
    var info : InfoModel? = null
    var name : String? = null
    var page = 1

    override fun layoutID(): Int = R.layout.fragment_episode
    override fun provideViewModel(): EpisodeFragmentViewModel = vm
    override fun bindViewModel(binding: FragmentEpisodeBinding) {
        binding.apply {
            viewModel = this@EpisodeFragment.viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getEpisode(name,page)
        observableViewModel()

        binding.seacrhViewEpisode.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchEpisode(query)
                vm.getEpisode(query,page)
                list.clear()
                (activity as MainActivityRAM).hideKeyboard()
                binding.seacrhViewEpisode.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    fun searchEpisode(seacrhKey : String?){
        if (seacrhKey != null) {
            Log.e("Kişi Ara", seacrhKey)
        }
    }

    fun observableViewModel(){
        vm.run {
            episodeLivaData.observe(viewLifecycleOwner, Observer {
                it.info?.let {
                    info = it
                }
                if (list.isNullOrEmpty()){
                    it.results?.let { resultData ->
                        list = resultData
                        adapterCreater()
                    }
                }else{
                    it.results?.let {newList ->
                        baseAdapter?.newList(newList)
                    }
                }
            })
            episodeLivaDataError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context,"Aradığınız bölüm bulunamadı... Tekrar seçim yapınız.", Toast.LENGTH_SHORT)
                    .show()
                binding.seacrhViewEpisode.setQuery("",true)
            })
        }
    }

    fun adapterCreater(){
        binding.imageViewClickEpisode.setOnClickListener {
            if (binding.recyclerViewEpisode.layoutManager == linearLayoutManager){
                binding.recyclerViewEpisode.layoutManager = gridLayoutManager
            }else if (binding.recyclerViewEpisode.layoutManager == gridLayoutManager){
                binding.recyclerViewEpisode.layoutManager = linearLayoutManager
            }
        }

        baseAdapter = RecyclerViewAdapterEpisode(requireContext(),list,this,this)
        binding.recyclerViewEpisode.layoutManager = linearLayoutManager
        binding.recyclerViewEpisode.adapter = baseAdapter
    }

    override fun getPage() {
        info?.next?.split("=")?.get(1)?.toInt()?.let {
            page = it
            vm.getEpisode(name,page)
        }
    }

    override fun click(id: Int?) {
        val intent = Intent(requireContext(), DetailActivityRAM::class.java)
        intent.putExtra(EPISODE_ID,id)
        startActivity(intent)
    }
}