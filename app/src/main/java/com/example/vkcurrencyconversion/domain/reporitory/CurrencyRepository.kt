package com.example.vkcurrencyconversion.domain.reporitory

import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.util.Resource

interface CurrencyRepository {

    suspend fun convert(amount: Double, from: String, to: String): Resource<Currency>
}