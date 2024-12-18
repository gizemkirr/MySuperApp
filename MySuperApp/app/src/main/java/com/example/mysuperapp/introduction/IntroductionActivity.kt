package com.example.mysuperapp.introduction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.common.BaseDataStore
import com.example.mysuperapp.apps.AppActivity
import com.example.mysuperapp.databinding.ActivityIntroductionBinding
import com.example.mysuperapp.introduction.fragments.IntroductionFragmentNews
import com.example.mysuperapp.introduction.fragments.IntroductionFragmentRAM
import com.example.mysuperapp.introduction.fragments.IntroductionFragmentWeathers
import com.example.mysuperapp.introduction.fragments.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class IntroductionActivity : AppCompatActivity() {

    private var binding : ActivityIntroductionBinding? = null
    private var viewPager : ViewPager2? = null
    private var tabLayout : TabLayout? = null

    @Inject
    lateinit var baseDataStore: BaseDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroductionBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        CoroutineScope(Dispatchers.IO).launch {
            baseDataStore.saveIntroduction()
        }

        viewPager = binding?.viewPagerIntroduction
        val fragments = listOf(IntroductionFragmentRAM(),IntroductionFragmentNews(),IntroductionFragmentWeathers())
        val adapter = ViewPagerAdapter(fragments,this)
        viewPager?.adapter = adapter

        tabLayout = binding?.tabLayoutIntroduction
        tabLayout?.let { viewPager?.let { it1 ->
            TabLayoutMediator(it,
                it1
            ){ tab, position -> }.attach()
        } }

        binding?.viewPagerIntroduction?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if (position == fragments.lastIndex){
                    binding?.imageViewRight?.visibility = View.VISIBLE
                }else{
                    binding?.imageViewRight?.visibility = View.GONE
                }
            }
        })

        binding?.imageViewRight?.setOnClickListener {
            val intent = Intent(this,AppActivity::class.java)
            startActivity(intent)
        }
    }
}
