package com.example.feature.rickandmorty.presentation.activities

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.common.BaseActivity
import com.example.feature.rickandmorty.R
import com.example.feature.rickandmorty.databinding.ActivityMainRamBinding
import com.example.feature.rickandmorty.presentation.fragments.CharacterFragment
import com.example.feature.rickandmorty.presentation.fragments.EpisodeFragment
import com.example.feature.rickandmorty.presentation.fragments.LocationFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivityRAM : BaseActivity<MainActivityRAMViewModel, ActivityMainRamBinding>() {

    private val vm : MainActivityRAMViewModel by viewModels()

    override fun layoutID(): Int = R.layout.activity_main_ram
    override fun provideViewModel(): MainActivityRAMViewModel = vm
    override fun bindViewModel(binding: ActivityMainRamBinding) {
        binding.apply {
            viewModel = this@MainActivityRAM.viewModel
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        replaceFragment(CharacterFragment())

        binding.bottomNavRAM.setOnItemSelectedListener {
            when(it.itemId){
                R.id.characterFragment -> {
                    replaceFragment(CharacterFragment())
                    true
                }
                R.id.locationFragment -> {
                    replaceFragment(LocationFragment())
                    true
                }
                R.id.episodeFragment -> {
                    replaceFragment(EpisodeFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun hideKeyboard(){
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun replaceFragment(fragment : Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayoutRAM,fragment)
        transaction.commit()
    }
}