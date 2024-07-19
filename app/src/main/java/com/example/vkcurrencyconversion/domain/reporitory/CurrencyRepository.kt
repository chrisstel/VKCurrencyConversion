package com.example.vkcurrencyconversion.domain.reporitory

import com.example.vkcurrencyconversion.data.network.response.ExchangeRateResponse
import com.example.vkcurrencyconversion.domain.model.Currency

interface CurrencyRepository {

    suspend fun convert(from: Currency, to: String): ExchangeRateResponse
}