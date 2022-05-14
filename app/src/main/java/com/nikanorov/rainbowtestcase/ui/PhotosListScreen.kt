package com.nikanorov.rainbowtestcase.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nikanorov.rainbowtestcase.R
import com.nikanorov.rainbowtestcase.model.Photo
import java.net.URLEncoder

@Composable
fun PhotosListScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState: State<List<Photo>>
) {
    PhotosListContent(modifier, navController, uiState)
}

@Composable
fun PhotosListContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState: State<List<Photo>>
) {
    Scaffold(
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
        PhotosList(modifier, navController, uiState)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosList(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    uiState: State<List<Photo>>
) {
    LazyVerticalGrid(cells = GridCells.Adaptive(120.dp)) {
        items(uiState.value) {
            PhotoPreviewItem(photo = it, navController = navController, modifier = modifier)
        }
    }

}

@Composable
fun PhotoPreviewItem(
    modifier: Modifier = Modifier,
    photo: Photo,
    navController: NavHostController
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
                    //отправим просто url в параметрах, чтоб не усложнять, в идеале конечно так делать не стоит.
                    navController.navigate("photoUrl/${URLEncoder.encode(photo.url, "UTF-8")}")
                }
                .padding(4.dp)
        )
    }
}