package com.success.photocatalog.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: String,
    val author: String,
    val width: Int = 800,
    val height: Int = 600,
    val url: String,
    val downloadUrl: String,
)