package com.success.photocatalog.data.api

import retrofit2.http.GET
import com.success.photocatalog.data.dto.PromoImageDto

//interface PhotoApi {
//    @GET("photos")
//    suspend fun getPhotos(
//        @Query("_page") page: Int,
//        @Query("_limit") limit: Int
//    ): List<PhotoDto>
//}

interface PromoImageApi {
    @GET("api/promo-images")
    suspend fun getPromoImages(): List<PromoImageDto>
}