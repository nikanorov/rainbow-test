package com.nikanorov.rainbowtestcase.data.photos

import com.nikanorov.rainbowtestcase.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    fun observePhotosList(): Flow<List<Photo>>
}
