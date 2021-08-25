package com.mytran.myapplication.api.response

import com.google.gson.annotations.SerializedName

data class CoinResponse (
    @SerializedName("data") val list : List<CoinData>,
)

data class CoinData(
    @SerializedName("base") val base : String,
    @SerializedName("counter") val counter : String,
    @SerializedName("buy_price") val buy_price : String,
    @SerializedName("sell_price") val sell_price : String,
    @SerializedName("icon") val icon : String,
    @SerializedName("name") val name : String,
)