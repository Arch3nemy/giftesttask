package com.alacrity.giftesttask.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.alacrity.giftesttask.App
import com.alacrity.giftesttask.GifTestTaskApp
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)
        setContent {
            GifTestTaskApp(context = this, homeViewModel = mainViewModel)
        }
    }

}