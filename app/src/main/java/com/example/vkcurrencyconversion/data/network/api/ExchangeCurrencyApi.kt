package com.example.vkcurrencyconversion.data.network.api

import com.example.vkcurrencyconversion.data.network.response.ExchangeRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeCurrencyApi {

    @GET("v1/latest")
    fun getExchangeRate(
        @Query("apikey") apiKey: String,
        @Query("base_currency") baseCurrency: String,
        @Query("currencies") currency: String
    ): Call<ExchangeRate>
}