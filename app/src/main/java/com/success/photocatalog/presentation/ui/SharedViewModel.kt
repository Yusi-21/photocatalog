package com.success.photocatalog.presentation.ui

import androidx.lifecycle.ViewModel
import com.success.photocatalog.domain.entity.Photo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    var selectedPhoto: Photo? = null
}