package com.nikanorov.rainbowtestcase.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.nikanorov.rainbowtestcase.R
import com.nikanorov.rainbowtestcase.ui.theme.photoPreviewBackgroundColor

@Composable
fun PhotoDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    photoUrl: String
) {
    PhotoDetailContent(photoUrl = photoUrl, modifier = modifier, navController = navController)
}

@Composable
fun PhotoDetailContent(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    photoUrl: String
) {
    //maybe better just use Column?
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.cd_back),
                            tint = MaterialTheme.colors.onSecondary
                        )
                    }
                }, backgroundColor = photoPreviewBackgroundColor
            )
        }
    ) {
        PhotoViewContent(photoUrl = photoUrl)
    }
}

@Composable
fun PhotoViewContent(modifier: Modifier = Modifier, photoUrl: String) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(photoPreviewBackgroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoViewItem(photoUrl = photoUrl, modifier = modifier.fillMaxSize())
    }
}


@Composable
fun PhotoViewItem(modifier: Modifier = Modifier, photoUrl: String) {
    AsyncImage(
        model = photoUrl,
        contentDescription = null,
        modifier = modifier
    )
}