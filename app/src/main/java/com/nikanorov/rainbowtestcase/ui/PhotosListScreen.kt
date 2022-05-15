package com.nikanorov.rainbowtestcase.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nikanorov.rainbowtestcase.R
import com.nikanorov.rainbowtestcase.model.Photo

@Composable
fun PhotosListScreen(
    modifier: Modifier = Modifier,
    uiState: State<PhotosViewModel.HomeUiState>,
    onOpenPhoto: (photoURL: String) -> Unit,
) {
    PhotosListContent(modifier, uiState, onOpenPhoto)
}

@Composable
fun PhotosListContent(
    modifier: Modifier = Modifier,
    uiState: State<PhotosViewModel.HomeUiState>,
    onOpenPhoto: (photoURL: String) -> Unit,
) {

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Left
                    )
                })
        }

    ) {

        //если есть ошибка, покажем снэкбар с ней
        if (uiState.value.errorMessage.isNotEmpty()) {
            val errorMessage = remember { uiState.value.errorMessage }
            LaunchedEffect(errorMessage) {
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = errorMessage,
                    duration = SnackbarDuration.Long
                )
            }
        }

        //загрузка
        if (uiState.value.isLoading) {
            LoadingScreen()
        }
        //список фото
        else {
            PhotosList(modifier, uiState, onOpenPhoto)
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosList(
    modifier: Modifier = Modifier,
    uiState: State<PhotosViewModel.HomeUiState>,
    onOpenPhoto: (photoURL: String) -> Unit,
) {
    LazyVerticalGrid(cells = GridCells.Adaptive(120.dp)) {
        items(uiState.value.photos) {
            PhotoPreviewItem(photo = it, modifier = modifier, onOpenPhoto = onOpenPhoto)
        }
    }
}

@Composable
fun PhotoPreviewItem(
    modifier: Modifier = Modifier,
    photo: Photo,
    onOpenPhoto: (photoURL: String) -> Unit,
) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(120.dp)
    ) {
        AsyncImage(
            model = photo.url,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clickable {
                    onOpenPhoto(photo.url)
                }
                .padding(4.dp)
        )
    }
}