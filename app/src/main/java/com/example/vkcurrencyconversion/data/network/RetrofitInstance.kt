package com.example.vkcurrencyconversion.data.network

import com.example.vkcurrencyconversion.BuildConfig
import com.example.vkcurrencyconversion.data.network.api.ExchangeCurrencyApi
import com.example.vkcurrencyconversion.data.network.response.ExchangeRate
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.freecurrencyapi.com/"
    private const val API_KEY = BuildConfig.API_KEY

    private val api: ExchangeCurrencyApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeCurrencyApi::class.java)
    }

    suspend fun getExchangeRate(from: String, to: String): Call<ExchangeRate> =
        api.getExchangeRate(
            apiKey = API_KEY,
            baseCurrency = from,
            currency = to
        )
}