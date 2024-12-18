package com.example.mysuperapp.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.common.BaseDataStore
import com.example.mysuperapp.apps.AppActivity
import com.example.mysuperapp.databinding.ActivitySplashScreenBinding
import com.example.mysuperapp.introduction.IntroductionActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private var binding : ActivitySplashScreenBinding? = null

    @Inject
    lateinit var baseDataStore: BaseDataStore

    var introductionOpen: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        CoroutineScope(Dispatchers.Main).launch {
            introductionOpen = baseDataStore.getIntroduction()
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (introductionOpen == true) {
                val intent = Intent(this, AppActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this,IntroductionActivity::class.java)
                startActivity(intent)
                finish()
            }
        },3000)
    }
}