package com.example.vkcurrencyconversion.di

import com.example.vkcurrencyconversion.presentation.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {

    viewModel<MainViewModel> {
        MainViewModel(
            convertCurrencyUseCase = get()
        )
    }
}