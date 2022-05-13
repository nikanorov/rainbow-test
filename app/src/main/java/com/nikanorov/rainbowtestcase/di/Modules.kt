package com.nikanorov.rainbowtestcase.di

import com.nikanorov.rainbowtestcase.BASE_API_URL
import com.nikanorov.rainbowtestcase.api.ApiService
import com.nikanorov.rainbowtestcase.data.photos.PhotosRepository
import com.nikanorov.rainbowtestcase.data.photos.impl.FlickrPhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RestApiModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_API_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .build()

    @Singleton
    @Provides
    fun provideService(retrofitClient: Retrofit): ApiService =
        retrofitClient.create(ApiService::class.java)


}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePhotosRepository(apiService: ApiService): PhotosRepository =
        FlickrPhotosRepository(apiService)
}
