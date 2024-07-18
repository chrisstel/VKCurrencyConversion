package com.example.vkcurrencyconversion.data.network

import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository

class CurrencyRepositoryImpl() : CurrencyRepository {

    override suspend fun convert(from: Currency, to: String): Currency {
        val currentRate = getCurrentRate(from.currencyType, to).data.rate

        return Currency(
            amount = currentRate*from.amount,
            currencyType = to
        )
    }

    private suspend fun getCurrentRate(from: String, to: String) = RetrofitInstance.getExchangeRate(from, to)
}