package com.nikanorov.rainbowtestcase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RainbowNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home",
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        //домашний экран со списком
        composable("home") {
            val photosViewModel: PhotosViewModel = hiltViewModel()
            val uiState = photosViewModel.uiState.collectAsState()
            PhotosListScreen(
                navController = navController,
                uiState = uiState,
            )
        }
        //отдельный экран с фото
        composable("photoUrl/{photoUrl}") {
            val photoUrl = it.arguments?.getString("photoUrl")
            photoUrl?.let { url -> PhotoDetailScreen(photoUrl = url) }
        }

    }

}
