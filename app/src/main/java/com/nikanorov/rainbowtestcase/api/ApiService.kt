package com.nikanorov.rainbowtestcase.api

import com.nikanorov.rainbowtestcase.FLICKR_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("?method=flickr.galleries.getPhotos&api_key=$FLICKR_API_KEY&format=json&nojsoncallback=1") //TODO: move api_key to Interceptor?
    suspend fun getPhotosFromGallery(@Query("gallery_id") galleryId: String): Response<PhotosResponse>
}


data class PhotosResponse(val photos: Photos){
    data class Photos(val photo: ArrayList<Photo>){
        data class Photo(val id: String, val server: String, val secret: String)
    }
}
