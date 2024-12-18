package com.example.feature.rickandmorty.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.common.BaseActivity
import com.example.feature.rickandmorty.R
import com.example.feature.rickandmorty.databinding.ActivityDetailRamBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivityRAM : BaseActivity<DetailActivityRAMViewModel, ActivityDetailRamBinding>() {

    private val vm: DetailActivityRAMViewModel by viewModels()

    companion object {
        const val CHARACTER_ID = "characterID"
        const val LOCATION_ID = "locationID"
        const val EPISODE_ID = "episodeID"
    }

    override fun layoutID(): Int = R.layout.activity_detail_ram
    override fun provideViewModel(): DetailActivityRAMViewModel = vm
    override fun bindViewModel(binding: ActivityDetailRamBinding) {
        binding.apply {
            viewModel = this@DetailActivityRAM.viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observableCharacter()
        observableLocation()
        observableEpisode()

        var dataCharacterID = intent.getIntExtra(CHARACTER_ID, 0)
        var dataLocationID = intent.getIntExtra(LOCATION_ID, 0)
        var dataEpisodeID = intent.getIntExtra(EPISODE_ID, 0)

        dataCharacterID.let {
            if (it > 0) {
                vm.getCharacterID(it)
            }
        }
        dataLocationID.let {
            if (it > 0) {
                vm.getLocationID(it)
            }
        }
        dataEpisodeID.let {
            if (it > 0) {
                vm.getEpisodeID(it)
            }
        }
    }

    fun observableCharacter() {
        vm.run {
            detailLiveDataCharacter.observe(this@DetailActivityRAM, Observer {
                firstObservable.set(it?.name)
                secondObservable.set(it?.status)
                thirdObservable.set(it?.species)
                fourthObservable.set(it?.type)
                fifthObservable.set(it?.gender)
                sixthObservable.set(it?.created)
                Glide.with(this@DetailActivityRAM).load(it.image).into(binding.imageViewCollapsingToolbar)
            })
        }
    }

    fun observableLocation() {
        vm.run {
            detailLiveDataLocation.observe(this@DetailActivityRAM, Observer {
                firstObservable.set(it?.name)
                secondObservable.set(it?.type)
                thirdObservable.set(it?.dimension)
                fourthObservable.set(it?.created)
                binding.imageViewCollapsingToolbar.setImageResource(R.drawable.ram_collapsing_toolbar)
            })
        }
    }

    fun observableEpisode() {
        vm.run {
            detailLiveDataEpisode.observe(this@DetailActivityRAM, Observer {
                firstObservable.set(it?.name)
                secondObservable.set(it?.air_date)
                thirdObservable.set(it?.episode)
                fourthObservable.set(it?.created)
                binding.imageViewCollapsingToolbar.setImageResource(R.drawable.ram_episode)
            })
        }
    }
}