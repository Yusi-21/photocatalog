package com.success.photocatalog.domain.usecase

import com.success.photocatalog.domain.entity.Photo
import com.success.photocatalog.domain.repository.PhotoRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        limit: Int = 20
    ): List<Photo> {
        return try {
            repository.getPhotos(page, limit)
        } catch (e: Exception) {
            println("UseCase error: ${e.message}")
            emptyList()
        }
    }
}