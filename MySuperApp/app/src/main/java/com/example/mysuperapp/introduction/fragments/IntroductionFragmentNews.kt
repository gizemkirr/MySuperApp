package com.example.mysuperapp.introduction.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysuperapp.R
import com.example.mysuperapp.databinding.FragmentIntroductionNewsBinding

class IntroductionFragmentNews : Fragment() {

    private var binding : FragmentIntroductionNewsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIntroductionNewsBinding.inflate(inflater, container, false)

        binding?.imageViewIntroductionNews?.setImageResource(R.drawable.news_introduction)
        binding?.textViewIntroductionNews?.text = getString(R.string.newsIntroduction)

        return binding?.root
    }

}