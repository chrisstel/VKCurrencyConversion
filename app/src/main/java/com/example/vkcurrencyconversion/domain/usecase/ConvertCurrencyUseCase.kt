package com.example.vkcurrencyconversion.domain.usecase

import com.example.vkcurrencyconversion.domain.model.Currency
import com.example.vkcurrencyconversion.domain.reporitory.CurrencyRepository

class ConvertCurrencyUseCase(
    private val currencyRepository: CurrencyRepository
) {

    suspend operator fun invoke(from: Currency, to: String): Currency =
        currencyRepository.convert(
            from = from,
            to = to
        )
}