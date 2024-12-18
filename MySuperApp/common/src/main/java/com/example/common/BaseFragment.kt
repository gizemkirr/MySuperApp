package com.example.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<rootViewModel : ViewModel, rootDataBinding : ViewDataBinding> : Fragment() {

    lateinit var viewModel : rootViewModel
    lateinit var binding : rootDataBinding

    abstract fun layoutID() : Int
    abstract fun provideViewModel() : rootViewModel
    abstract fun bindViewModel(binding : rootDataBinding)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,layoutID(),container,false)
        bindViewModel(binding)
        return binding.root
    }

}