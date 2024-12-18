package com.example.mysuperapp.introduction.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mysuperapp.R
import com.example.mysuperapp.databinding.FragmentIntroductionRamBinding

class IntroductionFragmentRAM : Fragment() {

    private var binding : FragmentIntroductionRamBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentIntroductionRamBinding.inflate(inflater, container, false)

        binding?.imageViewRAMIntroduction?.setImageResource(R.drawable.ram_introduction)
        binding?.textViewRAMIntroduction?.text = getString(R.string.ramIntroduction)

        return binding?.root
    }

}