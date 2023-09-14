package com.example.kotlin.mypokedexapp.framework.views

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.mypokedexapp.databinding.ActivitySplashscreenBinding
import com.example.kotlin.mypokedexapp.framework.viewmodel.SplashScreenViewModel

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashscreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeBinding()
        viewModel.onCreate()
        initializeObservers()
    }
    private fun passViewGoToMain(){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        startActivity(intent)
        finish()
    }
    private fun initializeBinding() {
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun initializeObservers() {
        viewModel.finishedLoading.observe(this) { finishedLoading ->
            if (finishedLoading) {
                passViewGoToMain()
            }
        }
    }
}