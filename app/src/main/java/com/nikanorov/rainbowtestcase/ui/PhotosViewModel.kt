package com.nikanorov.rainbowtestcase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikanorov.rainbowtestcase.data.photos.PhotosRepository
import com.nikanorov.rainbowtestcase.model.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosRepository: PhotosRepository
) : ViewModel() {

    data class HomeUiState(
        val isLoading: Boolean = true,
        val errorMessage: String = "",
        val photos: List<Photo> = listOf()
    )

    private val viewModelState = MutableStateFlow(HomeUiState(isLoading = true))

    //stateIn чтоб пережить изменения конфигурации
    val uiState = viewModelState
        .stateIn(
            scope = viewModelScope, started = WhileSubscribed(5000),
            initialValue = viewModelState.value
        )


    init {
        loadPhotos()
    }


    private fun loadPhotos() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            photosRepository.observePhotosList().catch { throwable ->
                viewModelState.update {
                    it.copy(
                        isLoading = false,
                        photos = emptyList(),
                        errorMessage = throwable.message.toString()
                    )
                }
            }.collect { photos ->
                viewModelState.update {
                    it.copy(
                        isLoading = false,
                        photos = photos,
                        errorMessage = ""
                    )
                }
            }
        }
    }
}