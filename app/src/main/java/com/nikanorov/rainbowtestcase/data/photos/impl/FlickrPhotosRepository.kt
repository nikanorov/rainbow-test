package com.nikanorov.rainbowtestcase.data.photos.impl

import com.nikanorov.rainbowtestcase.api.ApiService
import com.nikanorov.rainbowtestcase.api.PhotosResponse
import com.nikanorov.rainbowtestcase.data.photos.PhotosRepository
import com.nikanorov.rainbowtestcase.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlickrPhotosRepository @Inject constructor(private val apiService: ApiService) :
    PhotosRepository {

    //получим фото через обычный flow, но для большого колличества фоток, нужно было бы реализовать через Paging 3
    private val photosFlow: Flow<List<Photo>> = flow {

        //запросим даееы из API flickr
        val result =
            withContext(Dispatchers.IO) { apiService.getPhotosFromGallery() }

        when (result.isSuccessful) {
            true -> {
                val listOfPhotos = arrayListOf<Photo>()
                result.body()?.photos?.photo?.forEach { photo ->
                    listOfPhotos.add(photo.toAppPhoto())
                }
                emit(listOfPhotos)
            }
            false -> {
                throw Exception(result.code().toString())
            }
        }

    }

    override fun observePhotosList() = photosFlow

    private fun PhotosResponse.Photos.Photo.toAppPhoto() = Photo(
        id = this.id,
        //url construct: https://www.flickr.com/services/api/misc.urls.html
        url = "https://live.staticflickr.com/${this.server}/${this.id}_${this.secret}_b.jpg"
    )

}