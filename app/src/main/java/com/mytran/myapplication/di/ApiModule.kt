package com.mytran.myapplication.di

import com.mytran.myapplication.api.request.CoinServices
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(CoinServices::class.java) }
}