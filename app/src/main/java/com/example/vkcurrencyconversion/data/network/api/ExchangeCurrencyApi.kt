package com.example.vkcurrencyconversion.data.network.api

import com.example.vkcurrencyconversion.BuildConfig
import com.example.vkcurrencyconversion.data.network.response.ExchangeRate
import com.example.vkcurrencyconversion.domain.model.Currency
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeCurrencyApi {

    @GET("v1/latest")
    fun getExchangeRate(
        @Query("apikey") apiKey: String,
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currency: String
    ): ExchangeRate
}