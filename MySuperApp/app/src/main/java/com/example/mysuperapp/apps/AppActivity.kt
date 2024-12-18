package com.example.mysuperapp.apps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.feature.news.presentation.activities.MainActivityNews
import com.example.mysuperapp.R
import com.example.mysuperapp.databinding.ActivityAppBinding
import com.example.feature.rickandmorty.presentation.activities.MainActivityRAM
import com.example.feature.weathers.presentation.MainActivityWeathers

class AppActivity : AppCompatActivity() {

    private var binding : ActivityAppBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_app)

        binding?.textViewAppTitle?.text = getString(R.string.appBaslik)

        binding?.cardViewRAM?.setOnClickListener {
            val intent = Intent(this, MainActivityRAM::class.java)
            startActivity(intent)
        }
        binding?.imageViewAppRAM?.setImageResource(R.drawable.ram_introduction)

        binding?.cardViewNews?.setOnClickListener {
            val intent = Intent(this, MainActivityNews::class.java)
            startActivity(intent)
        }
        binding?.imageViewAppNews?.setImageResource(R.drawable.news_introduction)

        binding?.cardViewWeather?.setOnClickListener {
            val intent = Intent(this, MainActivityWeathers::class.java)
            startActivity(intent)
        }
        binding?.imageViewAppWeathers?.setImageResource(R.drawable.weathers_introduction)
    }
}