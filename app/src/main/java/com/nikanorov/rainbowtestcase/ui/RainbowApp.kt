package com.nikanorov.rainbowtestcase.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.nikanorov.rainbowtestcase.ui.theme.RainbowTheme

@Composable
fun RainbowApp() {
    RainbowTheme {
        val navController = rememberNavController()

        Scaffold(
            topBar = {}
        ) {
            RainbowNavGraph(navController = navController)
        }
    }
}