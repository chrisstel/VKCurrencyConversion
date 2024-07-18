package com.example.vkcurrencyconversion.di

import com.example.vkcurrencyconversion.data.network.CurrencyRepositoryImpl
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository
import org.koin.dsl.module

val dataModule = module {

    single<CurrencyRepository> {
        CurrencyRepositoryImpl()
    }
}