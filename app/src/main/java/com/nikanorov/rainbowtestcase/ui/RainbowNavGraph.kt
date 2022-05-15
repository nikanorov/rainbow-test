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
import java.net.URLEncoder

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
                uiState = uiState,
                //отправим просто url в параметрах, чтоб не усложнять, в идеале конечно так делать не стоит.
                onOpenPhoto = {
                    navController.navigate(
                        "photoUrl/${
                            URLEncoder.encode(
                                it,
                                "UTF-8"
                            )
                        }"
                    )
                }
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
                    onGoHome = { navController.navigateUp() }
                )
            }
        }

    }

}

@Composable
fun ShowSystemBar(systemBarsVisible: Boolean = true) {
    val systemUiController = rememberSystemUiController()
    systemUiController.apply {
        isSystemBarsVisible = systemBarsVisible
    }
}
