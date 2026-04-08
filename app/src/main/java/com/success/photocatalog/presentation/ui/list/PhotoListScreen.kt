package com.success.photocatalog.presentation.ui.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.success.photocatalog.domain.entity.Photo
import com.success.photocatalog.presentation.ui.SharedViewModel

@Composable
fun PhotoListScreen(
    onPhotoClick: (Photo) -> Unit,
    viewModel: PhotoListViewModel = hiltViewModel(),
    sharedViewModel: SharedViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (state) {
            is PhotoListState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is PhotoListState.Success -> {
                val photos = (state as PhotoListState.Success).photos
                PhotoGrid(
                    photos = photos,
                    onPhotoClick = { photo ->
                        sharedViewModel.selectedPhoto = photo
                        onPhotoClick(photo)
                    }
                )
            }

            is PhotoListState.Error -> {
                val message = (state as PhotoListState.Error).message
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Ошибка: $message",
                            color = MaterialTheme.colorScheme.error
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.loadPhotos() }) {
                            Text("Повторить")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PhotoGrid(
    photos: List<Photo>,
    onPhotoClick: (Photo) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.padding(top = 30.dp, bottom = 16.dp)
    ) {
        items(photos, key = { it.id }) { photo ->
            PhotoCard(
                photo = photo,
                onClick = { onPhotoClick(photo) }
            )
        }
    }
}