package com.example.mysuperapp.introduction.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysuperapp.R
import com.example.mysuperapp.databinding.FragmentIntroductionWeathersBinding

class IntroductionFragmentWeathers : Fragment() {

    private var binding : FragmentIntroductionWeathersBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIntroductionWeathersBinding.inflate(inflater, container, false)

        binding?.imageViewIntroductionWeathers?.setImageResource(R.drawable.weathers_introduction)
        binding?.textViewIntroductionWeathers?.text = getString(R.string.weathersIntroduction)

        return binding?.root
    }

}