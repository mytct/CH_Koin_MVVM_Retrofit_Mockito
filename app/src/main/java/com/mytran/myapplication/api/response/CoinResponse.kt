package com.mytran.myapplication.api.response

import com.google.gson.annotations.SerializedName

data class CoinResponse (
    @SerializedName("id") val id : Int,
    @SerializedName("name") val name : String? = null,
    @SerializedName("username") val username : String? = null,
    @SerializedName("email") val email : String? = null,
)