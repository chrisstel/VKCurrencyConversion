package com.example.vkcurrencyconversion.domain.usecase

import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository

class ConvertCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) {

    suspend operator fun invoke(amount: Double, from: String, to: String) = currencyRepository
        .convert(
            amount = amount,
            from = from,
            to = to
        )
}

