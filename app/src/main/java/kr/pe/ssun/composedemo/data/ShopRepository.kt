package kr.pe.ssun.composedemo.data

import kr.pe.ssun.composedemo.data.model.Shop
import javax.inject.Singleton

@Singleton
class ShopRepository(
    private val apiService: ApiService
) {

    suspend fun getShop(query: String, display: Int, start: Int, sort: String): Shop =
        apiService.getShop(query, display, start, sort)
}