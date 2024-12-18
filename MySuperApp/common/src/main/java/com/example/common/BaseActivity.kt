package com.example.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VM : ViewModel, BD : ViewDataBinding> : AppCompatActivity() {

    lateinit var viewModel : VM
    lateinit var binding : BD

    abstract fun layoutID() : Int
    abstract fun provideViewModel() : VM
    abstract fun bindViewModel(binding : BD)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        binding = DataBindingUtil.setContentView(this,layoutID())
        bindViewModel(binding)
    }

}