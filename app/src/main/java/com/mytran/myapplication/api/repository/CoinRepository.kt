package com.mytran.myapplication.api.repository

import com.mytran.myapplication.api.request.CoinServices

class CoinRepository(private val coinServices: CoinServices) {
    suspend fun repoGetListCoins() = coinServices.listCoins()
}