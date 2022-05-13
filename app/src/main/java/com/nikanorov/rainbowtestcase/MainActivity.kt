package com.nikanorov.rainbowtestcase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import com.nikanorov.rainbowtestcase.ui.RainbowApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RainbowApp()
        }
    }

}
