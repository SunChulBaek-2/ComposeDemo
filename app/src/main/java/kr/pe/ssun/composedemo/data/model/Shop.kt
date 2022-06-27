package kr.pe.ssun.composedemo.data.model

import com.google.gson.annotations.SerializedName

data class ShopItem(
    @SerializedName("productId") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("link") val link: String,
    @SerializedName("image") val image: String,
)

data class Shop(
    @SerializedName("total") val total: Int,
    @SerializedName("start") val start: Int,
    @SerializedName("display") val display: Int,
    @SerializedName("items") val items: List<ShopItem>
)