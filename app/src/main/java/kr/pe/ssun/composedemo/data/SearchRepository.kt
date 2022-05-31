package kr.pe.ssun.composedemo.data

import kr.pe.ssun.composedemo.data.model.ShopResult
import javax.inject.Singleton

@Singleton
class SearchRepository(
    private val searchService: SearchService
) {

    suspend fun search(
        query: String,
        display: Int? = null,
        start: Long? = null,
        sort: String? = null
    ): ShopResult = searchService.searchShop(query, display, start, sort)
}