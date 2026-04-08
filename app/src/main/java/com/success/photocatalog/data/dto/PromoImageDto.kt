package com.success.photocatalog.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PromoImageDto(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("imageUrl") val imageUrl: String
)