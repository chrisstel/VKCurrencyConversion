package com.example.vkcurrencyconversion.data.network.response

sealed class ExchangeRateResponse() {
    data class Success(val exchangeRate: ExchangeRate) : ExchangeRateResponse()

    data class Error(val errors: String?) : ExchangeRateResponse()

    data class Exception(val e: Throwable) : ExchangeRateResponse()
}