package com.myproject.imagefiltersapp.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myproject.imagefiltersapp.repositories.SavedImageRepository
import com.myproject.imagefiltersapp.utilities.Coroutines
import java.io.File

class SavedImagesViewModel(private val savedImageRepository: SavedImageRepository) : ViewModel() {

    private val savedImageDataState = MutableLiveData<SavedImageDataState>()
    val savedImageUiState: LiveData<SavedImageDataState> get() = savedImageDataState

    fun loadSavedImages() {
        Coroutines.io {
            kotlin.runCatching {
                emitSavedImageUiState(isLoading = true)
                savedImageRepository.loadSavedImages()
            }.onSuccess { savedImages ->
                if (savedImages.isNullOrEmpty()) {
                    emitSavedImageUiState(error = "No Image Found")
                }else{
                    emitSavedImageUiState(savedImages = savedImages)
                }
            }.onFailure {
                emitSavedImageUiState(error = it.message.toString())
            }
        }
    }

    private fun emitSavedImageUiState(
        isLoading: Boolean = false,
        savedImages: List<Pair<File, Bitmap>>? = null,
        error: String? = null
    ) {
        val dataState = SavedImageDataState(isLoading, savedImages, error)
        savedImageDataState.postValue(dataState)
    }

    data class SavedImageDataState(
        val isLoading: Boolean,
        val savedImages: List<Pair<File, Bitmap>>?,
        val error: String?
    )
}