package com.example.vkcurrencyconversion.domain.reporitory

import com.example.vkcurrencyconversion.domain.model.Currency

interface CurrencyRepository {

    suspend fun convert(from: Currency, to: String): Currency
}