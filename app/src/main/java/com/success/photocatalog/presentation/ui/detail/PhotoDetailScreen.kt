package com.success.photocatalog.presentation.ui.detail

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.success.photocatalog.utils.DownloadUtils.downloadPhoto
import com.success.photocatalog.domain.entity.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoDetailScreen(
    photo: Photo,
    baseUrl: String = "http://192.168.31.116:8080"
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var isDownloading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Детали фото") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fullUrl = "$baseUrl${photo.downloadUrl}"
            Image(
                painter = rememberAsyncImagePainter(model = fullUrl),
                contentDescription = "Photo by ${photo.author}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Автор: ${photo.author}",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Размеры: ${photo.width} × ${photo.height}")

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    scope.launch {
                        isDownloading = true
                        val result = downloadPhoto(
                            context = context,
                            url = "$baseUrl${photo.downloadUrl}",
                            fileName = "photo_${photo.id}.jpg"
                        )
                        isDownloading = false

                        withContext(Dispatchers.Main) {
                            if (result.isSuccess) {
                                Toast.makeText(context, "Фото сохранено в Downloads", Toast.LENGTH_LONG).show()
                            } else {
                                Toast.makeText(context, "Ошибка при сохранении: ${result.exceptionOrNull()?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                },
                enabled = !isDownloading
            ) {
                if (isDownloading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = MaterialTheme.colorScheme.onPrimary)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(if (isDownloading) "Скачивание..." else "Скачать фото")
            }
        }
    }
}