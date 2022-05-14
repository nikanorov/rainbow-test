package com.nikanorov.rainbowtestcase.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

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

            //покажем SystemBar для списка фото
            ShowSystemBar()

            val photosViewModel: PhotosViewModel = hiltViewModel()
            val uiState = photosViewModel.uiState.collectAsState()
            PhotosListScreen(
                navController = navController,
                uiState = uiState,
            )
        }
        //отдельный экран с фото
        composable("photoUrl/{photoUrl}") {

            //спрячем SystemBar для превью
            ShowSystemBar(false)

            val photoUrl = it.arguments?.getString("photoUrl")
            photoUrl?.let { url ->
                PhotoDetailScreen(
                    photoUrl = url,
                    navController = navController
                )
            }
        }

    }

}

@Composable
fun ShowSystemBar(systemBarsVisible: Boolean = true){
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        isSystemBarsVisible = systemBarsVisible
    }
}
