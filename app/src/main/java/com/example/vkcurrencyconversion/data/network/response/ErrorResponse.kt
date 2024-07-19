package com.example.vkcurrencyconversion.data.network.response

data class ErrorResponse(
    val message: String,
    val errors: Errors,
    val info: String,
)

data class Errors(
    val base_currency: List<String>?,
    val currencies: List<String>?
)