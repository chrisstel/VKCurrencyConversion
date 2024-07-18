package com.example.vkcurrencyconversion.data.network

import android.util.Log
import com.example.vkcurrencyconversion.data.network.response.ExchangeRateResponse
import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository

class CurrencyRepositoryImpl() : CurrencyRepository {

    override suspend fun convert(from: Currency, to: String): Currency {
        val currentRate = getCurrentRate(from.currencyType, to).data[to]
        Log.d("RETROFIT", "Current rate: $currentRate")

        return Currency(
            amount = 0*from.amount,
            currencyType = to
        )
    }

    private suspend fun getCurrentRate(from: String, to: String): ExchangeRateResponse = RetrofitInstance.getExchangeRate(from, to)
}