package com.example.giphy.presentation.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.giphy.R
import com.example.giphy.presentation.view.search.GiphySearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}