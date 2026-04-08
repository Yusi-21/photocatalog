package com.success.photocatalog.presentation.ui.list

import com.success.photocatalog.domain.entity.Photo

sealed class PhotoListState {
    object Loading: PhotoListState()
    data class Success(val photos: List<Photo>) : PhotoListState()
    data class Error(val message: String) : PhotoListState()
}