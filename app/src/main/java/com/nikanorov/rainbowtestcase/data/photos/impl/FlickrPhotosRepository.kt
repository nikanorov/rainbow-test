package com.nikanorov.rainbowtestcase.data.photos.impl

import android.util.Log
import com.nikanorov.rainbowtestcase.FLICKR_GALLERY_ID
import com.nikanorov.rainbowtestcase.api.ApiService
import com.nikanorov.rainbowtestcase.data.photos.PhotosRepository
import com.nikanorov.rainbowtestcase.model.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FlickrPhotosRepository @Inject constructor(private val apiService: ApiService) :
    PhotosRepository {

    private val TAG = "rainbow-repository"

    //получим фото через обычный flow, но для большого колличества фоток, нужно было бы реализовать через Paging 3
    private val photosFlow: Flow<List<Photo>> = flow {

        //я okhttp3/retrofit не использовал, пока не понял как красиво обработать какой-нибудь DNS error.
        val result = try {
            withContext(Dispatchers.IO) { apiService.getPhotosFromGallery(FLICKR_GALLERY_ID) }
        } catch (e: Exception) {
            e.printStackTrace()
            return@flow
        }

        when (result.isSuccessful) {
            true -> {
                val listOfPhotos = arrayListOf<Photo>()
                result.body()?.photos?.photo?.forEach { photo ->
                    listOfPhotos.add(
                        Photo(
                            id = photo.id,
                            //url construct: https://www.flickr.com/services/api/misc.urls.html
                            url = "https://live.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.jpg"
                        )
                    )
                }
                emit(listOfPhotos)
            }
            false -> {
                Log.d(TAG, result.message())
            }
        }

    }

    override fun observePhotosList() = photosFlow
}