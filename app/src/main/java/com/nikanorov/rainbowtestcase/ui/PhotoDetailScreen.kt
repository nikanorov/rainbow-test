package com.nikanorov.rainbowtestcase.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun PhotoDetailScreen(modifier: Modifier = Modifier, photoUrl: String) {
    Column {
        PhotoViewItem(photoUrl = photoUrl, modifier = modifier)
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