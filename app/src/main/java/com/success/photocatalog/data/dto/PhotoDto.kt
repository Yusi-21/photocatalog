package com.success.photocatalog.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//@Serializable
//data class PhotoDto(
//    @SerialName("id") val id: String,
//    @SerialName("author") val author: String,
//    @SerialName("width") val width: Int,
//    @SerialName("height") val height: Int,
//    @SerialName("url") val url: String,
//    @SerialName("download_url") val downloadUrl: String
//)

@Serializable
data class PhotoDto(
    @SerialName("id") val id: Int,
    @SerialName("albumId") val albumId: Int,
    @SerialName("title") val title: String,
    @SerialName("url") val url: String,
    @SerialName("thumbnailUrl") val thumbnailUrl: String
)