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
import com.example.feature.rickandmorty.data.repo.database.CharacterFavoriteModel
import com.example.feature.rickandmorty.databinding.FragmentCharacterBinding
import com.example.feature.rickandmorty.listener.ClickFavoriteListener
import com.example.feature.rickandmorty.listener.ClickListener
import com.example.feature.rickandmorty.listener.PaginationListener
import com.example.feature.rickandmorty.data.model.charactermodel.CharacterResultModel
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAM
import com.example.feature.rickandmorty.presentation.activities.DetailActivityRAM.Companion.CHARACTER_ID
import com.example.feature.rickandmorty.presentation.activities.FavoriteActivity
import com.example.feature.rickandmorty.presentation.activities.MainActivityRAM
import com.example.feature.rickandmorty.presentation.recyclerview.RecyclerViewAdapterCharacter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment : BaseFragment<CharacterFragmentViewModel, FragmentCharacterBinding>(),
    PaginationListener, ClickListener , ClickFavoriteListener{

    private val vm : CharacterFragmentViewModel by viewModels()
    var linearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
    var gridLayoutManager = GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
    private var baseAdapter : RecyclerViewAdapterCharacter? = null
    var list = ArrayList<CharacterResultModel>()
    var favoriteList = ArrayList<CharacterFavoriteModel>()
    var info : InfoModel? = null
    var name : String? = null
    var page = 1

    override fun layoutID(): Int = R.layout.fragment_character
    override fun provideViewModel(): CharacterFragmentViewModel = vm
    override fun bindViewModel(binding: FragmentCharacterBinding) {
        binding.apply {
            viewModel = this@CharacterFragment.viewModel
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.getFavoriteCharacters()
        vm.getCharacter(page, name)
        observableViewModel()

        binding.imageViewFavorite.setOnClickListener {
            val intent = Intent(context, FavoriteActivity::class.java)
            startActivity(intent)
        }

        binding.searchViewCharacter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchCharacter(query)
                vm.getCharacter(page, query)
                list.clear()
                (activity as MainActivityRAM).hideKeyboard()
                binding.searchViewCharacter.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    fun searchCharacter(searchKey : String?){
        if (searchKey != null) {
            Log.e("Kişi Ara", searchKey)
        }
    }

    fun observableViewModel(){
        vm.run {
            characterLiveData.observe(viewLifecycleOwner, Observer {
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
            characterLiveDataError.observe(viewLifecycleOwner, Observer {
                Toast.makeText(context,"Aradığın karakter bulunamadı... Tekrar seçim yapınız.",Toast.LENGTH_SHORT)
                    .show()
                binding.searchViewCharacter.setQuery("",true)
            })
            favoriteCharacterLiveData.observe(viewLifecycleOwner, Observer {
                favoriteList = it as ArrayList<CharacterFavoriteModel>
            })
        }
    }

    fun adapterCreater(){
        binding.imageViewClickCharacter.setOnClickListener {
            if(binding.recyclerViewCharacter.layoutManager == linearLayoutManager){
                binding.recyclerViewCharacter.layoutManager = gridLayoutManager
            }else if(binding.recyclerViewCharacter.layoutManager == gridLayoutManager){
                binding.recyclerViewCharacter.layoutManager = linearLayoutManager }
        }
        baseAdapter = RecyclerViewAdapterCharacter(requireContext(),list,favoriteList,this,this,this)
        binding.recyclerViewCharacter.layoutManager = linearLayoutManager
        binding.recyclerViewCharacter.adapter = baseAdapter
    }

    override fun getPage() {
        info?.next?.split("=")?.get(1)?.toInt()?.let {
            page = it
            vm.getCharacter(page,name)
        }
    }

    override fun click(id: Int?) {
        val intent = Intent(requireContext(), DetailActivityRAM::class.java)
        intent.putExtra(CHARACTER_ID,id)
        startActivity(intent)
    }

    override fun getFavorite(id: Int?, addOrDelete : Boolean) {
        var model = list.find {
            it.id == id
        }
        if (addOrDelete){
            CoroutineScope(Dispatchers.Main).launch {
                model?.let {
                    model.id?.let { it1 ->
                        model.name?.let { it2 ->
                            model.image?.let { it3 ->
                                vm.crepo.saveCharacter(
                                    it1,
                                    it2,
                                    it3
                                )
                            }
                        }
                    }
                }
            }
        }else{
            CoroutineScope(Dispatchers.Main).launch {
                model?.let {
                    model.id?.let { it1 ->
                        vm.crepo.deleteCharacter(it1)
                    }
                }
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            val newList = vm.crepo.getCharacters()
            baseAdapter?.newFavoriteList(newList)
        }
    }
}