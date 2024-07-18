package com.example.vkcurrencyconversion.data.network.api

import com.example.vkcurrencyconversion.data.network.response.ExchangeRateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeCurrencyApi {

    @GET("v1/latest")
    suspend fun getExchangeRate(
        @Query("apikey") apiKey: String,
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currency: String
    ): ExchangeRateResponse
}