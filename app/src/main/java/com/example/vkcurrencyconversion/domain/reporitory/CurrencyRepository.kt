package com.example.vkcurrencyconversion.domain.reporitory

import com.example.vkcurrencyconversion.data.network.response.ExchangeRateResponse

interface CurrencyRepository {

    suspend fun convert(amount: Double, from: String, to: String): ExchangeRateResponse
}