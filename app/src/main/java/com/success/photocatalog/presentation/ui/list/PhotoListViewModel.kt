package com.success.photocatalog.presentation.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.success.photocatalog.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoListViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<PhotoListState>(PhotoListState.Loading)
    val state: StateFlow<PhotoListState> = _state.asStateFlow()

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            _state.value = PhotoListState.Loading
            try {
                val photos = getPhotosUseCase()
                _state.value = PhotoListState.Success(photos)
            } catch (e: Exception) {
                _state.value = PhotoListState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
}