package com.success.photocatalog.domain.repository

import com.success.photocatalog.domain.entity.Photo

interface PhotoRepository {
    suspend fun getPhotos(page: Int, limit: Int): List<Photo>
}