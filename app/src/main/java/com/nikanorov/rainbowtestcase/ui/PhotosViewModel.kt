package com.nikanorov.rainbowtestcase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikanorov.rainbowtestcase.data.photos.PhotosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    photosRepository: PhotosRepository
) : ViewModel() {

    //stateIn чтоб пережить изменения конфигурации
    val uiState = photosRepository.observePhotosList().stateIn(
        initialValue = emptyList(),
        started = WhileSubscribed(5000), scope = viewModelScope,
    )

}