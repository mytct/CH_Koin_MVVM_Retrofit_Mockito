package com.mytran.myapplication.api.request

import com.mytran.myapplication.api.response.CoinResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CoinServices {
    @Headers("Accept: application/json")
    @GET("price/all_prices_for_mobile")
    suspend fun listCoins(@Query("counter_currency") currency: String = "USD"): List<CoinResponse>
}