package com.example.vkcurrencyconversion.di

import com.example.vkcurrencyconversion.domain.usecase.ConvertCurrencyUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<ConvertCurrencyUseCase> {
        ConvertCurrencyUseCase(currencyRepository = get())
    }
}