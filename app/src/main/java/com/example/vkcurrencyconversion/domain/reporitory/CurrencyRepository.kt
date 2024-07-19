package com.example.vkcurrencyconversion.domain.reporitory

import com.example.vkcurrencyconversion.utils.response.ExchangeRateResponse

interface CurrencyRepository {

    suspend fun convert(amount: Double, from: String, to: String): ExchangeRateResponse
}