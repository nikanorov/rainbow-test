package com.nikanorov.rainbowtestcase.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nikanorov.rainbowtestcase.model.Photo
import java.net.URLEncoder

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotosListScreen(
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
    AsyncImage(
        model = photo.url,
        contentDescription = null,
        modifier = modifier
            .clickable {
                //отправим просто url в параметрах, чтоб не усложнять, в идеале конечно так делать не стоит.
                navController.navigate("photoUrl/${URLEncoder.encode(photo.url, "UTF-8")}")
            }
            .padding(8.dp)
    )
}