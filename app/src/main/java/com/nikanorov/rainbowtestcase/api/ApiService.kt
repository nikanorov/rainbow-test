package com.nikanorov.rainbowtestcase.api

import com.nikanorov.rainbowtestcase.FLICKR_API_KEY
import com.nikanorov.rainbowtestcase.FLICKR_GALLERY_ID
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=flickr.galleries.getPhotos&format=json&nojsoncallback=1")
    suspend fun getPhotosFromGallery(
        @Query("gallery_id") galleryId: String = FLICKR_GALLERY_ID,
        @Query("api_key") api_key: String = FLICKR_API_KEY
    ): Response<PhotosResponse>
}


data class PhotosResponse(val photos: Photos) {
    data class Photos(val photo: List<Photo>) {
        data class Photo(val id: String, val server: String, val secret: String)
    }
}
