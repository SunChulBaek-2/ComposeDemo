package kr.pe.ssun.composedemo.data.model

import com.google.gson.annotations.SerializedName

data class ShopResult(
    @SerializedName("total") val total: Int,
    @SerializedName("items") val items: List<ShopItem>
)

data class ShopItem(
    @SerializedName("productId") val productId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("image") val image: String,
    @SerializedName("lprice") val lprice: String,
    @SerializedName("hprice") val hprice: String
)