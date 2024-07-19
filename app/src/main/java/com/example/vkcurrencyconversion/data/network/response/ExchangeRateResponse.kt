package com.example.vkcurrencyconversion.data.network.response

import com.example.vkcurrencyconversion.data.network.model.Errors
import com.example.vkcurrencyconversion.domain.model.Currency

sealed class ExchangeRateResponse {

    data class Success(val currency: Currency) : ExchangeRateResponse()

    data class Error(
        val message: String? = null,
        val errors: Errors? = null,
        val info: String? = null
    ) : ExchangeRateResponse()

    data class Exception(val e: Throwable) : ExchangeRateResponse()
}