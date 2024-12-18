package com.example.feature.rickandmorty.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.common.BaseActivity
import com.example.feature.rickandmorty.R
import com.example.feature.rickandmorty.data.repo.database.CharacterFavoriteModel
import com.example.feature.rickandmorty.databinding.ActivityFavoriteBinding
import com.example.feature.rickandmorty.presentation.recyclerview.RecyclerViewAdapterFavoriteCharacter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : BaseActivity<FavoriteActivityViewModel,ActivityFavoriteBinding>() {

    private val vm : FavoriteActivityViewModel by viewModels()
    private var baseAdapter : RecyclerViewAdapterFavoriteCharacter? = null

    override fun layoutID(): Int = R.layout.activity_favorite
    override fun provideViewModel(): FavoriteActivityViewModel = vm
    override fun bindViewModel(binding: ActivityFavoriteBinding) {
        binding.apply {
            viewModel = this@FavoriteActivity.viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observableViewModel()
        vm.getCharacters()

    }

    fun observableViewModel(){
        vm.run {
            characterFavoriteLiveData.observe(this@FavoriteActivity, Observer {
                adapterCreater(it)
            })
        }
    }

    fun adapterCreater(list : List<CharacterFavoriteModel>){
        baseAdapter = RecyclerViewAdapterFavoriteCharacter(this,list)
        binding.recyclerViewFavorite.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.recyclerViewFavorite.adapter = baseAdapter
    }
}