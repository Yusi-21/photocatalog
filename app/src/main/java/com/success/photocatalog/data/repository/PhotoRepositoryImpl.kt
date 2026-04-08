package com.success.photocatalog.data.repository

//import com.success.nobel.data.api.PhotoApi
import com.success.photocatalog.data.api.PromoImageApi
import com.success.photocatalog.data.mapper.PhotoMapper
import com.success.photocatalog.domain.entity.Photo
import com.success.photocatalog.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
//    private val api: PhotoApi
    private val api: PromoImageApi
) : PhotoRepository {
    override suspend fun getPhotos(page: Int, limit: Int): List<Photo> {
        val photosDto = api.getPromoImages()
        return PhotoMapper.toEntityList(photosDto)
    }
}