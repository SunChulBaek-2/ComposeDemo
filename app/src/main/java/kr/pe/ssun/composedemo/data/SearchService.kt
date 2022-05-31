package kr.pe.ssun.composedemo.data

import kr.pe.ssun.composedemo.data.model.ShopResult
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET("shop.json")
    suspend fun searchShop(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Long? = null,
        @Query("sort") sort: String? = null
    ): ShopResult
}