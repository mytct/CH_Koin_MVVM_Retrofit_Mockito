package com.mytran.myapplication.di

import com.mytran.myapplication.api.repository.CoinRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CoinRepository(get()) }
}