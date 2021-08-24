package com.mytran.myapplication.di

import com.mytran.myapplication.ui.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { MainViewModel() }
}