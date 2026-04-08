package com.success.photocatalog.data.mapper

import com.success.photocatalog.data.dto.PromoImageDto
import com.success.photocatalog.domain.entity.Photo

object PhotoMapper {
//    fun toEntity(dto: PhotoDto): Photo {
//        val workingImageUrl = "https://picsum.photos/id/${dto.id}/200/150"
//        return Photo(
//            id = dto.id.toString(),
//            author = dto.title.take(50),
//            width = 200,
//            height = 150,
//            url = workingImageUrl,
//            downloadUrl = workingImageUrl
//        )
//        return Photo(
//            id = dto.id.toString(),
//            author = dto.title.take(50),  // title становится "автором"
//            width = 600,   // значение по умолчанию
//            height = 400,  // значение по умолчанию
//            url = dto.url,               // большая картинка
//            downloadUrl = dto.url        // для скачивания используем ту же ссылку
//        )
//    }

//    fun toEntityList(dtoList: List<PhotoDto>): List<Photo> {
//        return dtoList.map { toEntity(it) }
//    }

    fun toEntity(dto: PromoImageDto): Photo {
        return Photo(
            id = dto.id.toString(),
            author = dto.title,
            width = 800,
            height = 600,
            url = dto.imageUrl,
            downloadUrl = dto.imageUrl
        )
    }

    fun toEntityList(dtoList: List<PromoImageDto>): List<Photo> {
        return dtoList.map { toEntity(it) }
    }
}